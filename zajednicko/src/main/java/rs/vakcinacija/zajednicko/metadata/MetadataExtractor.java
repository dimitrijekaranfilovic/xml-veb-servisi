package rs.vakcinacija.zajednicko.metadata;

import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.xml.sax.SAXException;

public class MetadataExtractor {
	private final TransformerFactory transformerFactory;
	
	public MetadataExtractor() {
		transformerFactory = new TransformerFactoryImpl();
	}

	public void extractMetadata(InputStream in, OutputStream out) throws TransformerException {
		StreamSource transformSource = new StreamSource(new ByteArrayInputStream(GRDDL.getBytes()));
		Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
		grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
		grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamSource source = new StreamSource(in);
		StreamResult result = new StreamResult(out);
		grddlTransformer.transform(source, result);
	}

	private final String GRDDL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"\n" +
			"<!-- RDFa To RDF/XML XSL Transformation -->\n" +
			"<!-- Retrieved from: https://www.w3.org/TR/grddl-primer/RDFa2RDFXML.xsl -->\n" +
			"<stylesheet\n" +
			"    xmlns:xsl  =\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"\n" +
			"    xmlns:h    =\"http://www.w3.org/1999/xhtml\"\n" +
			"    xmlns      =\"http://www.w3.org/1999/XSL/Transform\"\n" +
			"    xmlns:rdf  =\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n" +
			"\n" +
			"<output indent=\"yes\" method=\"xml\" media-type=\"application/rdf+xml\" encoding=\"UTF-8\" omit-xml-declaration=\"yes\"/>\n" +
			"\n" +
			"<!-- base of the current XHTML page -->\n" +
			"<variable name='base' select=\"//*/@xml:base[position()=1]\"/>\n" +
			"\n" +
			"<!-- url of the current XHTML page if provided by the XSLT engine -->\n" +
			"<param name='url' select=\"''\"/>\n" +
			"\n" +
			"<!-- this contains the URL of the source document whether it was provided by the base or as a parameter e.g. http://example.org/bla/file.html-->\n" +
			"<variable name='this' >\n" +
			"\t<choose>\n" +
			"\t\t<when test=\"string-length($base)>0\"><value-of select=\"$base\"/></when>\n" +
			"\t\t<otherwise><value-of select=\"$url\"/></otherwise>\n" +
			"\t</choose>\n" +
			"</variable>\n" +
			"\n" +
			"<!-- this_location contains the location the source document e.g. http://example.org/bla/ -->\n" +
			"<variable name='this_location' >\n" +
			"\t<call-template name=\"get-location\"><with-param name=\"url\" select=\"$this\"/></call-template>\n" +
			"</variable>\n" +
			"\n" +
			"<!-- this_root contains the root location of the source document e.g. http://example.org/ -->\n" +
			"<variable name='this_root' >\n" +
			"\t<call-template name=\"get-root\"><with-param name=\"url\" select=\"$this\"/></call-template>\n" +
			"</variable>\n" +
			"\n" +
			"\n" +
			"<!-- templates for parsing - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->\n" +
			"\n" +
			"<!--Start the RDF generation-->\n" +
			"<template match=\"/\">\n" +
			"<rdf:RDF xmlns:rdf =\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\n" +
			"  <apply-templates />\n" +
			"</rdf:RDF>\n" +
			"</template>\n" +
			"\n" +
			"\n" +
			"\n" +
			"<!-- match RDFa element -->\n" +
			"<template match=\"*[attribute::property or attribute::rel or attribute::rev]\">\n" +
			"\n" +
			"   <!-- identify suject -->\n" +
			"   <variable name=\"subject\"> \n" +
			"    <choose>\n" +
			"    \t\n" +
			"     <!-- an attribute about was specified on the node -->\n" +
			"     <when test=\"self::*/attribute::about\">\n" +
			"       <call-template name=\"expand-curie-or-uri\"><with-param name=\"curie_or_uri\" select=\"@about\"/></call-template>\n" +
			"     </when>\n" +
			"\n" +
			"     <!-- current node is a meta or a link in the body and with no about attribute -->\n" +
			"     <when test=\"(self::h:link or self::h:meta) and not( ancestor::h:head ) and not(attribute::about)\">\n" +
			"     \t<call-template name=\"self-curie-or-uri\"><with-param name=\"node\" select=\"parent::*\"/></call-template>\n" +
			"     </when>\n" +
			"\n" +
			"     <!-- current node is a meta or a link in the body and with no about attribute -->\n" +
			"     <when test=\"(self::h:link or self::h:meta) and not( ancestor::h:head ) and not(attribute::about)\">\n" +
			"     \t<call-template name=\"self-curie-or-uri\"><with-param name=\"node\" select=\"parent::*\"/></call-template>\n" +
			"     </when>\n" +
			"\n" +
			"     <!-- current node is a meta or a link in the head and with no about attribute -->\n" +
			"     <when test=\"(self::h:link or self::h:meta) and ( ancestor::h:head ) and not(attribute::about)\">\n" +
			"     \t<value-of select=\"$this\"/>\n" +
			"     </when>\n" +
			"     \n" +
			"     <!-- current has a parent with an id \n" +
			"     <when test=\"parent::*/attribute::id\"> \n" +
			"      <value-of select=\"concat($this,'#',parent::*/attribute::id)\"/>\n" +
			"     </when> -->\n" +
			"     \n" +
			"     <!-- an about was specified on its ancestors or the ancestor had a rel or a rev attribute but no href. -->\n" +
			"     <when test=\"ancestor::*[attribute::about or ( not(attribute::href) and ( attribute::rel or attribute::rev) )][position()=1]\"> \n" +
			"     \t<variable name=\"selected_ancestor\" select=\"ancestor::*[attribute::about or ( not(attribute::href) and (attribute::rel or attribute::rev) )][position()=1]\"/> \n" +
			"     \t<choose>\n" +
			"     \t\t<when test=\"$selected_ancestor//*[position()=1]/attribute::about\">\n" +
			"     \t\t\t<call-template name=\"expand-curie-or-uri\"><with-param name=\"curie_or_uri\" select=\"$selected_ancestor/attribute::about\"/></call-template>\n" +
			"     \t\t</when>\n" +
			"     \t\t<otherwise>\n" +
			"     \t\t\t<call-template name=\"self-curie-or-uri\"><with-param name=\"node\" select=\"$selected_ancestor\"/></call-template>\n" +
			"     \t\t</otherwise>\n" +
			"     \t</choose>\t\n" +
			"     </when>\n" +
			"     \n" +
			"     <otherwise> <!-- it must be about the current document -->\n" +
			"     \t<value-of select=\"$this\"/>\n" +
			"     </otherwise>\n" +
			"\n" +
			"    </choose>\n" +
			"   </variable>\n" +
			"   \n" +
			"   \n" +
			"   <!-- identify object -->\n" +
			"   <if test=\"@rel or @rev\">\n" +
			"     <variable name=\"object\">\n" +
			"       <choose>\n" +
			"\t     <when test=\"@href\"> \n" +
			"\t\t   <call-template name=\"expand-curie-or-uri\"><with-param name=\"curie_or_uri\" select=\"@href\"/></call-template>\n" +
			"\t     </when>\n" +
			"\t     <otherwise>\n" +
			"\t     \t<call-template name=\"self-curie-or-uri\"><with-param name=\"node\" select=\".\"/></call-template>\n" +
			"\t     </otherwise>\n" +
			"       </choose>\n" +
			"     </variable>\n" +
			"   \n" +
			"     <if test=\"@rel\">\n" +
			"       <call-template name=\"relation\">\n" +
			"        <with-param name=\"subject\" select =\"$subject\" />\n" +
			"        <with-param name=\"object\" select =\"$object\" />\n" +
			"        <with-param name=\"predicate\" select =\"@rel\"/>\n" +
			"       </call-template>       \n" +
			"     </if>\n" +
			"\n" +
			"     <if test=\"@rev\">\n" +
			"       <call-template name=\"relation\">\n" +
			"        <with-param name=\"subject\" select =\"$object\" />\n" +
			"        <with-param name=\"object\" select =\"$subject\" />\n" +
			"        <with-param name=\"predicate\" select =\"@rev\"/>\n" +
			"       </call-template>      \n" +
			"     </if>\n" +
			"   </if>\n" +
			"\n" +
			"   \n" +
			"   <!-- we have a property -->\n" +
			"   <if test=\"@property\">\n" +
			"   \t\n" +
			"   \t <!-- identify language -->\n" +
			"   \t <variable name=\"language\" select=\"string(ancestor-or-self::*/attribute::xml:lang[position()=1])\" />\n" +
			"   \t \n" +
			"     <variable name=\"expended-pro\"><call-template name=\"expand-ns\"><with-param name=\"qname\" select=\"@property\"/></call-template></variable>\n" +
			"\n" +
			"      <choose>\n" +
			"       <when test=\"@content\"> <!-- there is a specific content -->\n" +
			"         <call-template name=\"property\">\n" +
			"          <with-param name=\"subject\" select =\"$subject\" />\n" +
			"          <with-param name=\"object\" select =\"@content\" />\n" +
			"          <with-param name=\"datatype\" select =\"@datatype\" />\n" +
			"          <with-param name=\"predicate\" select =\"@property\"/>\n" +
			"          <with-param name=\"attrib\" select =\"'true'\"/>\n" +
			"          <with-param name=\"language\" select =\"$language\"/>\n" +
			"         </call-template>   \n" +
			"       </when>\n" +
			"       <otherwise> <!-- there is no specific content; we use the value of element -->\n" +
			"         <call-template name=\"property\">\n" +
			"          <with-param name=\"subject\" select =\"$subject\" />\n" +
			"          <with-param name=\"object\" select =\".\" />\n" +
			"          <with-param name=\"datatype\" select =\"@datatype\" />\n" +
			"          <with-param name=\"predicate\" select =\"@property\"/>\n" +
			"          <with-param name=\"attrib\" select =\"'false'\"/>\n" +
			"          <with-param name=\"language\" select =\"$language\"/>\n" +
			"         </call-template> \n" +
			"       </otherwise>\n" +
			"      </choose>\n" +
			"   </if>\n" +
			"\n" +
			"   <!-- we have a class \n" +
			"   <if test=\"@class\"> \n" +
			"     <variable name=\"expended-class\"><call-template name=\"expand-ns\"><with-param name=\"qname\" select=\"@class\"/></call-template></variable>        \n" +
			"\t\t <element name = \"rdf:Description\">\n" +
			"\t\t   <attribute name=\"rdf:about\"><value-of select=\"$expanded-about\" /></attribute>\n" +
			"\t\t   <element name = \"rdf:type\">\n" +
			"\t\t     <attribute name=\"rdf:resource\"><value-of select=\"$expended-class\" /></attribute>\n" +
			"\t\t   </element>     \n" +
			"\t\t </element>\n" +
			"\t </if> -->\n" +
			"\n" +
			"   <apply-templates /> \n" +
			"   \n" +
			"</template>\n" +
			"\n" +
			"\n" +
			"\n" +
			"<!-- named templates to process URIs - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->\n" +
			"\n" +
			"  <!-- tokenize a string using space as a delimiter -->\n" +
			"  <template name=\"tokenize\">\n" +
			"    <param name=\"string\" />\n" +
			"  \t<if test=\"string-length($string)>0\">\n" +
			"  \t\t<choose>\n" +
			"  \t\t\t<when test=\"contains($string,' ')\">\n" +
			"\t\t\t\t<value-of select=\"normalize-space(substring-before($string,' '))\"/>\n" +
			"\t\t\t\t<call-template name=\"tokenize\"><with-param name=\"string\" select=\"normalize-space(substring-after($string,' '))\"/></call-template>  \t  \t\t\t\t\n" +
			"  \t\t\t</when>\n" +
			"  \t\t\t<otherwise><value-of select=\"$string\"/></otherwise>\n" +
			"  \t\t</choose>\n" +
			"  \t</if>\n" +
			"  </template>\n" +
			"\n" +
			"  <!-- get file location from URL -->\n" +
			"  <template name=\"get-location\">\n" +
			"    <param name=\"url\" />\n" +
			"  \t<if test=\"string-length($url)>0 and contains($url,'/')\">\n" +
			"  \t\t<value-of select=\"concat(substring-before($url,'/'),'/')\"/>\n" +
			"  \t\t<call-template name=\"get-location\"><with-param name=\"url\" select=\"substring-after($url,'/')\"/></call-template>\n" +
			"  \t</if>\n" +
			"  </template>\n" +
			"\n" +
			"  <!-- get root location from URL -->\n" +
			"  <template name=\"get-root\">\n" +
			"    <param name=\"url\" />\n" +
			"\t<choose>\n" +
			"\t\t<when test=\"contains($url,'//')\">\n" +
			"\t\t\t<value-of select=\"concat(substring-before($url,'//'),'//',substring-before(substring-after($url,'//'),'/'),'/')\"/>\n" +
			"\t\t</when>\n" +
			"\t\t<otherwise>UNKOWN ROOT</otherwise>\n" +
			"\t</choose>    \n" +
			"  </template>\n" +
			"\n" +
			"  <!-- return namespace of a qname -->\n" +
			"  <template name=\"return-ns\" >\n" +
			"    <param name=\"qname\" />\n" +
			"    <variable name=\"ns_prefix\" select=\"substring-before($qname,':')\" />\n" +
			"    <variable name=\"name\" select=\"substring-after($qname,':')\" />\n" +
			"    <value-of select=\"ancestor-or-self::*/namespace::*[name()=$ns_prefix][position()=1]\" />\n" +
			"  </template>\n" +
			"\n" +
			"\n" +
			"  <!-- expand namespace of a qname -->\n" +
			"  <template name=\"expand-ns\" >\n" +
			"    <param name=\"qname\" />\n" +
			"    <variable name=\"ns_prefix\" select=\"substring-before($qname,':')\" />\n" +
			"    <variable name=\"name\" select=\"substring-after($qname,':')\" />\n" +
			"    <variable name=\"ns_uri\" select=\"ancestor-or-self::*/namespace::*[name()=$ns_prefix][position()=1]\" />\n" +
			"    <value-of select=\"concat($ns_uri,$name)\" />\n" +
			"  </template>\n" +
			"\n" +
			"  <!-- determines the CURIE / URI of a node -->\n" +
			"  <template name=\"self-curie-or-uri\" >\n" +
			"    <param name=\"node\" />\n" +
			"    <choose>\n" +
			"     <when test=\"$node/attribute::about\"> <!-- we have an about attribute to extend -->\n" +
			"       <call-template name=\"expand-curie-or-uri\"><with-param name=\"curie_or_uri\" select=\"$node/attribute::about\"/></call-template>\n" +
			"     </when>\n" +
			"     <when test=\"$node/attribute::id\"> <!-- we have an id attribute to extend -->\n" +
			"       <value-of select=\"concat($this,'#',$node/attribute::id)\" />\n" +
			"     </when>\n" +
			"     <otherwise>blank node <value-of select=\"concat('#',generate-id($node))\" /></otherwise>\n" +
			"    </choose>\n" +
			"  </template>  \n" +
			"\n" +
			"\n" +
			"\n" +
			"  <!-- expand CURIE / URI -->\n" +
			"  <template name=\"expand-curie-or-uri\" >\n" +
			"    <param name=\"curie_or_uri\" />\n" +
			"    <choose>\n" +
			"     <when test=\"starts-with($curie_or_uri,'[_:')\"> <!-- we have a CURIE blank node -->\n" +
			"      <value-of select=\"concat('blank node ',substring-after(substring-before($curie_or_uri,']'),'[_:'))\"/>\n" +
			"     </when>\n" +
			"     <when test=\"starts-with($curie_or_uri,'[')\"> <!-- we have a CURIE between square brackets -->\n" +
			"      <call-template name=\"expand-ns\"><with-param name=\"qname\" select=\"substring-after(substring-before($curie_or_uri,']'),'[')\"/></call-template>\n" +
			"     </when>\n" +
			"     <when test=\"starts-with($curie_or_uri,'#')\"> <!-- we have an anchor -->\n" +
			"      <value-of select=\"concat($this,$curie_or_uri)\" />\n" +
			"     </when>\n" +
			"     <when test=\"string-length($curie_or_uri)=0\"> <!-- empty anchor means the document itself -->\n" +
			"      <value-of select=\"$this\" />\n" +
			"     </when>\n" +
			"     <when test=\"not(starts-with($curie_or_uri,'[')) and contains($curie_or_uri,':')\"> <!-- it is a URI -->\n" +
			"      <value-of select=\"$curie_or_uri\" />\n" +
			"     </when>     \n" +
			"     <when test=\"not(contains($curie_or_uri,'://')) and not(starts-with($curie_or_uri,'/'))\"> <!-- relative URL -->\n" +
			"      <value-of select=\"concat($this_location,$curie_or_uri)\" />\n" +
			"     </when>\n" +
			"     <when test=\"not(contains($curie_or_uri,'://')) and (starts-with($curie_or_uri,'/'))\"> <!-- URL from root domain -->\n" +
			"      <value-of select=\"concat($this_root,substring-after($curie_or_uri,'/'))\" />\n" +
			"     </when>\n" +
			"     <otherwise>UNKNOWN CURIE URI</otherwise>\n" +
			"    </choose>\n" +
			"  </template>  \n" +
			"  \n" +
			"  <!-- returns the first predicate in a list separated by spaces -->\n" +
			"  <template name=\"get-first-predicate\">\n" +
			"  \t<param name=\"predicate\" />\n" +
			"\t<if test=\"string-length($predicate)>0\">\n" +
			"\t\t<choose>\n" +
			"\t\t\t<when test=\"contains($predicate,' ')\">\n" +
			"\t\t\t\t<value-of select=\"normalize-space(substring-before($predicate,' '))\"/>\t\t\t\n" +
			"\t\t\t</when>\n" +
			"\t\t\t<otherwise><value-of select=\"$predicate\" /></otherwise>\n" +
			"\t\t</choose>\n" +
			"\t</if>\n" +
			"  </template>\n" +
			"\n" +
			"<!-- named templates to generate RDF - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->\n" +
			"\n" +
			"  <template name=\"recursive-copy\">\n" +
			"    <copy>\n" +
			"\t <for-each select=\"node()\">\n" +
			"\t\t<call-template name=\"recursive-copy\" />\n" +
			"\t </for-each>\n" +
			"    </copy>\n" +
			"  </template>\n" +
			"  \n" +
			"  <!-- generate an RDF statement for a relation -->\n" +
			"  <template name=\"relation\" >\n" +
			"    <param name=\"subject\" />\n" +
			"    <param name=\"predicate\" />\n" +
			"    <param name=\"object\" />\n" +
			"  \n" +
			"    <!-- test for multiple predicates -->\n" +
			"    <variable name=\"single-predicate\"><call-template name=\"get-first-predicate\"><with-param name=\"predicate\" select=\"$predicate\"/></call-template></variable>\n" +
			"    \n" +
			"    <!-- get namespace of the predicate -->\n" +
			"    <variable name=\"predicate-ns\"><call-template name=\"return-ns\"><with-param name=\"qname\" select=\"$single-predicate\"/></call-template></variable>\n" +
			"    \n" +
			"    <choose>\n" +
			"     <when test=\"string-length($predicate-ns)>0\"> <!-- there is a known namespace for the predicate -->\n" +
			"\t    <element name = \"rdf:Description\">\n" +
			"\t      <choose>\n" +
			"\t      \t<when test=\"starts-with($subject,'blank node ')\"><attribute name=\"rdf:nodeID\"><value-of select=\"substring-after($subject,'blank node ')\" /></attribute></when>\n" +
			"\t      \t<otherwise><attribute name=\"rdf:about\"><value-of select=\"$subject\" /></attribute></otherwise>\n" +
			"\t      </choose>\n" +
			"\t      <element name = \"{$single-predicate}\" namespace=\"{$predicate-ns}\">\n" +
			"\t        <choose>\n" +
			"\t      \t  <when test=\"starts-with($object,'blank node ')\"><attribute name=\"rdf:nodeID\"><value-of select=\"substring-after($object,'blank node ')\" /></attribute></when>\n" +
			"\t      \t  <otherwise><attribute name=\"rdf:resource\"><value-of select=\"$object\" /></attribute></otherwise>\n" +
			"\t        </choose>\n" +
			"\t      </element>     \n" +
			"\t    </element>\n" +
			"     </when>\n" +
			"     <otherwise> <!-- generate a comment for debug -->\n" +
			"       <xsl:comment>Could not produce the triple for: <value-of select=\"$subject\" /> - <value-of select=\"$single-predicate\" /> - <value-of select=\"$object\" /></xsl:comment>\n" +
			"     </otherwise>\n" +
			"    </choose>\n" +
			"\n" +
			"    <!-- recursive call for multiple predicates -->\n" +
			"    <variable name=\"other-predicates\" select=\"normalize-space(substring-after($predicate,' '))\" />\n" +
			"    <if test=\"string-length($other-predicates)>0\">\n" +
			"\t\t<call-template name=\"relation\">\n" +
			"\t\t\t<with-param name=\"subject\" select=\"$subject\"/>\n" +
			"\t\t\t<with-param name=\"predicate\" select=\"$other-predicates\"/>\n" +
			"\t\t\t<with-param name=\"object\" select=\"$object\"/>\n" +
			"\t\t</call-template>    \t\n" +
			"    </if>\n" +
			"\n" +
			"  </template>\n" +
			"\n" +
			"\n" +
			"  <!-- generate an RDF statement for a property -->\n" +
			"  <template name=\"property\" >\n" +
			"    <param name=\"subject\" />\n" +
			"    <param name=\"predicate\" />\n" +
			"    <param name=\"object\" />\n" +
			"    <param name=\"datatype\" />\n" +
			"    <param name=\"attrib\" /> <!-- is the content from an attribute ? true /false -->\n" +
			"    <param name=\"language\" />\n" +
			"\n" +
			"    <!-- test for multiple predicates -->\n" +
			"    <variable name=\"single-predicate\"><call-template name=\"get-first-predicate\"><with-param name=\"predicate\" select=\"$predicate\"/></call-template></variable>\n" +
			"     \n" +
			"    <!-- get namespace of the predicate -->    \n" +
			"    <variable name=\"predicate-ns\"><call-template name=\"return-ns\"><with-param name=\"qname\" select=\"$single-predicate\"/></call-template></variable>\n" +
			"\n" +
			"    <choose>\n" +
			"     <when test=\"string-length($predicate-ns)>0\"> <!-- there is a known namespace for the predicate -->\n" +
			"\t    <element name = \"rdf:Description\">\n" +
			"\t      <choose>\n" +
			"\t      \t<when test=\"starts-with($subject,'blank node ')\"><attribute name=\"rdf:nodeID\"><value-of select=\"substring-after($subject,'blank node ')\" /></attribute></when>\n" +
			"\t      \t<otherwise><attribute name=\"rdf:about\"><value-of select=\"$subject\" /></attribute></otherwise>\n" +
			"\t      </choose>\n" +
			"\t      <element name = \"{$single-predicate}\" namespace=\"{$predicate-ns}\">\n" +
			"\t      <if test=\"string-length($language)>0\"><attribute name=\"xml:lang\"><value-of select=\"$language\" /></attribute></if>\n" +
			"\t      <choose>\n" +
			"\t        <when test=\"$datatype='plainliteral'\">\n" +
			"\t         <choose>\n" +
			"\t         \t<when test=\"$attrib='true'\"> <!-- content is in an attribute -->\n" +
			"\t         \t  <value-of select=\"string($object)\" />\n" +
			"\t            </when>\n" +
			"\t         \t<otherwise> <!-- content is in the element -->\n" +
			"\t\t\t\t <value-of select=\"$object\" />\n" +
			"\t\t\t\t</otherwise>\n" +
			"\t\t\t </choose>\n" +
			"\t        </when>\n" +
			"\t        <when test=\"string-length($datatype)>0\">\n" +
			"\t        \t<!-- there is a datatype other than XMLLiteral -->\n" +
			"\t         <variable name=\"expended-datatype\"><call-template name=\"expand-ns\"><with-param name=\"qname\" select=\"$datatype\"/></call-template></variable>\n" +
			"\t         <attribute name=\"rdf:datatype\"><value-of select=\"$expended-datatype\" /></attribute>\n" +
			"\t         <choose>\n" +
			"\t         \t<when test=\"$attrib='true'\"> <!-- content is in an attribute -->\n" +
			"\t         \t  <value-of select=\"string($object)\" />\n" +
			"\t            </when>\n" +
			"\t         \t<otherwise> <!-- content is in the element -->\n" +
			"\t\t\t\t <value-of select=\"$object\" />\n" +
			"\t\t\t\t</otherwise>\n" +
			"\t\t\t </choose>\n" +
			"\t        </when>\n" +
			"\t        <otherwise> <!-- there is no datatype -->\n" +
			"\t         <choose>\n" +
			"\t         \t<when test=\"$attrib='true'\"> <!-- content is in an attribute -->\n" +
			"\t         \t  <attribute name=\"rdf:datatype\"><value-of select=\"'http://www.w3.org/2000/01/rdf-schema#Literal'\" /></attribute>\n" +
			"\t         \t  <value-of select=\"string($object)\" />\n" +
			"\t            </when>\n" +
			"\t         \t<otherwise> <!-- content is in the element -->\n" +
			"\t         \t <attribute name=\"rdf:datatype\"><value-of select=\"'http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral'\" /></attribute>\n" +
			"\t\t\t\t <for-each select=\"$object/node()\"> \n" +
			"\t\t\t\t\t<call-template name=\"recursive-copy\" />\n" +
			"\t\t\t\t </for-each>\n" +
			"\t\t\t\t</otherwise>\n" +
			"\t\t\t </choose>\t \n" +
			"\t        </otherwise>\n" +
			"\t      </choose>\n" +
			"\t      </element>        \n" +
			"\t    </element>\n" +
			"     </when>\n" +
			"     <otherwise> <!-- generate a comment for debug -->\n" +
			"       <xsl:comment>Could not produce the triple for: <value-of select=\"$subject\" /> - <value-of select=\"$single-predicate\" /> - <value-of select=\"$object\" /></xsl:comment>\n" +
			"     </otherwise>\n" +
			"    </choose>\n" +
			"\n" +
			"    <!-- recursive call for multiple predicates -->\n" +
			"    <variable name=\"other-predicates\" select=\"normalize-space(substring-after($predicate,' '))\" />\n" +
			"    <if test=\"string-length($other-predicates)>0\">\n" +
			"\t\t<call-template name=\"property\">\n" +
			"\t\t\t<with-param name=\"subject\" select=\"$subject\"/>\n" +
			"\t\t\t<with-param name=\"predicate\" select=\"$other-predicates\"/>\n" +
			"\t\t\t<with-param name=\"object\" select=\"$object\"/>\n" +
			"\t\t\t<with-param name=\"datatype\" select=\"$datatype\"/>\n" +
			"\t\t\t<with-param name=\"attrib\" select=\"$attrib\"/>\n" +
			"\t\t\t<with-param name=\"language\" select=\"$language\"/>\n" +
			"\t\t</call-template>    \t\n" +
			"    </if>\n" +
			"     \n" +
			"  </template>\n" +
			"\n" +
			"\n" +
			"<!-- ignore the rest of the DOM -->\n" +
			"<template match=\"text()|@*|*\"><apply-templates /></template>\n" +
			"\n" +
			"\n" +
			"</stylesheet>\n";
}
