<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/izvestaj_o_imunizaciji">
    <xsl:attribute-set name="myBorder">
        <xsl:attribute name="border">1px solid black</xsl:attribute>
    </xsl:attribute-set>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvestaj-o-imunizaciji-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="izvestaj-o-imunizaciji-page">
                <fo:flow flow-name="xsl-region-body" margin-left="80px">

                    <fo:block>
                        <fo:block margin-left="150px" linefeed-treatment="preserve" font-family="SourceSansPro" font-size="20px" font-weight="bold"
                                  padding="1px">
                            Извештај о имунизацији
                        </fo:block>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                        Извештај се односи на период од
                        <fo:inline linefeed-treatment="preserve" font-family="SourceSansPro" font-size="11px" font-weight="bold">
                            <xsl:value-of select="substring(//b:period_od, 9, 2)"/>.<xsl:value-of
                                select="substring(//b:period_od, 6, 2)"/>.<xsl:value-of
                                select="substring(//b:period_od, 0, 5)"/>.
                        </fo:inline>
                        до
                        <fo:inline linefeed-treatment="preserve" font-family="SourceSansPro" font-size="11px" font-weight="bold">
                            <xsl:value-of select="substring(//b:period_do, 9, 2)"/>.<xsl:value-of
                                select="substring(//b:period_do, 6, 2)"/>.<xsl:value-of
                                select="substring(//b:period_do, 0, 5)"/>.
                        </fo:inline>
                    </fo:block>

                    <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                        У напоменутом временском интервалу је:
                        <fo:list-block>
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block></fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="10mm">
                                        <fo:inline>
                                           -   поднето
                                        </fo:inline>
                                        <fo:inline font-weight="bold" linefeed-treatment="preserve">
                                            <xsl:value-of select="//b:podneto_dokumenata_o_interesovanju"/>
                                        </fo:inline>
                                        докумената о интересовању за имунизацију
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>

                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block></fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body>
                                    <fo:block margin-left="10mm">
                                        <fo:inline>
                                            -   примљено
                                        </fo:inline>
                                        <fo:inline font-weight="bold" linefeed-treatment="preserve">
                                            <xsl:value-of select="//b:primljeno_zahteva_za_sertifikat"/>
                                        </fo:inline>
                                        захтева за дигитални зелени сертификат, од који је
                                        <fo:inline font-weight="bold" linefeed-treatment="preserve">
                                            <xsl:value-of select="//b:izdato_zahteva_za_sertifikat"/>
                                        </fo:inline>
                                        издато
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </fo:list-block>
                    </fo:block>

                    <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                        Дато је
                        <fo:inline font-weight="bold" linefeed-treatment="preserve">
                            <xsl:value-of select="sum(//b:data_doza/b:broj_datih_doza)"/>
                        </fo:inline>
                        доза вакцине против COVID-19 вируса у
                        следећој количини:
                        <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                            <fo:table  border="1px solid black" border-collapse="collapse" border-separation="6pt">

                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-header>
                                    <fo:table-row>
                                        <fo:table-cell text-align="center" xsl:use-attribute-sets="myBorder">
                                            <fo:block font-weight="bold">Редни број дозе</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center" xsl:use-attribute-sets="myBorder">
                                            <fo:block font-weight="bold">Број датих доза</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-header>

                                <fo:table-body>
                                    <xsl:for-each select="//b:data_doza">
                                        <fo:table-row>
                                            <fo:table-cell text-align="center" xsl:use-attribute-sets="myBorder">
                                                <fo:block>
                                                    <xsl:value-of select="b:redni_broj"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="center" xsl:use-attribute-sets="myBorder">
                                                <fo:block>
                                                    <xsl:value-of select="b:broj_datih_doza"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="20mm">
                            Расподела по произвођачима:
                            <xsl:for-each select="//b:stavka">
                                <fo:block margin-left="10mm">
                                    -  <fo:inline font-weight="bold">
                                        <xsl:value-of select="b:proizvodjac"/>-<xsl:value-of select="b:broj_doza"/>
                                    </fo:inline>
                                    доза
                                </fo:block>
                            </xsl:for-each>
                        </fo:block>
                        <fo:block margin-top="20mm">
                            <fo:table>
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>
                                                Датум издавања:
                                                <fo:inline text-decoration="underline">
                                                    <xsl:value-of select="substring(//b:datum_izdavanja, 9, 2)"/>.<xsl:value-of
                                                        select="substring(//b:datum_izdavanja, 6, 2)"/>.<xsl:value-of
                                                        select="substring(//b:datum_izdavanja, 0, 5)"/>.
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>
                                                ____________________________
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block>
                                                Потпис
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
<!--                        <fo:block margin-top="20mm">-->
<!--                            Датум издавања:-->
<!--                            <fo:inline text-decoration="underline">-->
<!--                                <xsl:value-of select="substring(//b:datum_izdavanja, 9, 2)"/>.<xsl:value-of-->
<!--                                    select="substring(//b:datum_izdavanja, 6, 2)"/>.<xsl:value-of-->
<!--                                    select="substring(//b:datum_izdavanja, 0, 5)"/>.-->
<!--                            </fo:inline>-->
<!--                        </fo:block>-->


                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>