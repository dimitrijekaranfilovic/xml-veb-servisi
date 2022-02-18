<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/FORMAT"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/izvestaj_o_imunizaciji"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvestaj-o-imunizaciji-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="izvestaj-o-imunizaciji-page">
                <fo:block>
                    <fo:block linefeed-treatment="preserve" font-family="SourceSansPro" font-size="20px" font-weight="bold"
                              padding="1px">
                        Извештај о имунизацији
                    </fo:block>
                </fo:block>
                <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                    Извештај се односи на период од
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="bold">
                        <xsl:value-of select="substring(//b:period_od, 0, 11)"/>
                    </fo:block>
                    до
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="bold">
                        <xsl:value-of select="substring(//b:period_od, 0, 11)"/>
                    </fo:block>
                </fo:block>

                <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                    У напоменутом временском интервалу је:
                    <fo:list-block>
                        <fo:list-item>
                            <fo:list-item-label>
                                <fo:block></fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body>
                                <fo:block>
                                    Поднето
                                    <fo:block font-weight="bold">
                                        <xsl:value-of select="//b:podneto_dokumenata_o_interesovanju"/>
                                    </fo:block>
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item>
                            <fo:list-item-label>
                                <fo:block></fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body>
                                <fo:block>
                                    примљено
                                    <fo:block font-weight="bold">
                                        <xsl:value-of select="//b:primljeno_zahteva_za_sertifikat"/>
                                    </fo:block>
                                    захтева за дигитални зелени сертификат, од који је
                                    <fo:block font-weight="bold">
                                        <xsl:value-of select="//b:izdato_zahteva_za_sertifikat"/>
                                    </fo:block>
                                    издато
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>
                    </fo:list-block>
                </fo:block>

                <fo:block font-family="SourceSansPro" font-size="11px" margin-top="10mm">
                    Дато је
                    <fo:block font-weight="bold">
                        <xsl:value-of select="sum(//b:data_doza/b:broj_datih_doza)"/>
                    </fo:block>
                    доза вакцине против COVID-19 вируса у
                    следећој количини:
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="bold" margin-top="10mm">
                        <fo:table>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="30%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>Држављанство</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>1) Република Србија</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>ЈМБГ:
                                            <xsl:value-of select="//b:jmbg"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>2)<xsl:value-of select="//b:naziv_drzavljanstva"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="//b:broj_pasosa"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                </fo:block>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>