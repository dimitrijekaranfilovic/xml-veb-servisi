<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:fo="http://www.w3.org/1999/XSL/Format"
xmlns:za="https://www.vakcinacija.rs/zajednicko"
xmlns:b="https://www.vakcinacija.rs/interesovanje">
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
                <fo:simple-page-master master-name="interesovanje-page">
                    <fo:region-body margin="1.25in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
                
            <fo:page-sequence master-reference="interesovanje-page">
            	<fo:flow flow-name="xsl-region-body">
            		<fo:block font-family="SourceSansPro" font-size="17px" font-weight="bold" text-align="center">
            			Исказивање интересовања за вакцинисање против COVID-19
            		</fo:block>
            		<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="12px" font-weight="normal">
            			Одаберите опцију:
            		</fo:block>
            		<xsl:variable name="x" select="//b:drzavljanstvo"/>
            		<fo:block margin-left="20mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Држављанин Републике Србије
            			<xsl:choose>
					      	<xsl:when test="$x = 'DRZAVLJANIN_REPUBLIKE_SRBIJE'">
								✓
		                    </xsl:when>	
		                </xsl:choose>
            		</fo:block>
            		<fo:block margin-left="20mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Страни држављанин са боравком у РС
            			<xsl:choose>
					      	<xsl:when test="$x = 'STRANI_DRZAVLJANIN_SA_BORAVKOM_U_RS'">
								✓
		                    </xsl:when>	
		                </xsl:choose>
            		</fo:block>
            		<fo:block margin-left="20mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Страни држављанин без боравка у РС
            			<xsl:choose>
					      	<xsl:when test="$x = 'STRANI_DRZAVLJANIN_BEZ_BORAVKA_U_RS'">
								✓
		                    </xsl:when>	
		                </xsl:choose>
            		</fo:block>
            		<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			ЈМБГ: <xsl:value-of select="//b:jmbg"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Име: <xsl:value-of select="//za:ime"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Презиме: <xsl:value-of select="//za:prezime"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Адреса електронске поште: <xsl:value-of select="//za:email"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Број мобилног телефона (навести број у формату 06X..... без размака и цртица): <xsl:value-of select="//za:broj_mobilnog"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица): <xsl:value-of select="//za:broj_fiksnog"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Одаберите локацију где желите да примите вакцину (унесите општину): <xsl:value-of select="//b:odabrana_lokacija_primanja_vakcine"/>
            		</fo:block>
            		<fo:block>
						<fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="1pt"/>  
					</fo:block>
					<fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Исказујем интересовање да примим искључиво вакцину следећих произвођача за
						који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и
						квалитет и изда дозволу за употребу лека:
            		</fo:block>
            		<xsl:for-each select="//b:proizvodjac">
				    	<fo:block margin-left="20mm" margin-top="2mm" font-family="SourceSansPro" font-size="11px" font-weight="normal"><xsl:value-of select="text()"/></fo:block>
				    </xsl:for-each>
				    <fo:block margin-top="5mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Да ли сте добровољни давалац крви?
            		</fo:block>
            		<xsl:variable name="x" select="//b:davalac_krvi"/>
            		<fo:block margin-left="20mm" margin-top="2mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Да
            			<xsl:choose>
					      	<xsl:when test="$x = 'true'">
		                      ✓
		                    </xsl:when>	
		                </xsl:choose>
            		</fo:block>
            		<fo:block margin-left="20mm" margin-top="2mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			Не
            			<xsl:choose>
					      	<xsl:when test="$x = 'false'">
		                      ✓
		                    </xsl:when>	
		                </xsl:choose>
            		</fo:block>
            		<fo:block margin-top="25mm" font-family="SourceSansPro" font-size="11px" font-weight="normal">
            			дана 
            			<fo:inline border-bottom="solid" border-bottom-width="0.2mm">
            				<xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of select="substring(//b:datum, 6, 2)"/>.
            			</fo:inline> 20
            			<fo:inline border-bottom="solid" border-bottom-width="0.2mm">
            				<xsl:value-of select="substring(//b:datum, 3, 2)"/>
	            		</fo:inline>
	            		године
	            	</fo:block>
	            	<fo:block>
	            		<fo:float float="end">
		            		<fo:block padding-left="20mm" padding-right="20mm" padding-top="2mm" font-family="SourceSansPro" font-size="11px" font-weight="normal" border-top="solid" border-top-width="0.2mm">
		            			Потпис
		            		</fo:block>
	            		</fo:float>
            		</fo:block>
            	</fo:flow>
            </fo:page-sequence>
            
		</fo:root>
	</xsl:template>
</xsl:stylesheet>