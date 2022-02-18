<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/potvrda_o_vakcinaciji">
    <xsl:attribute-set name="myBorder">
        <xsl:attribute name="border">1px solid black</xsl:attribute>
    </xsl:attribute-set>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="potvrda-o-vakcinaciji-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="potvrda-o-vakcinaciji-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                            <fo:external-graphic content-height="scale-to-fit" height="2in"  content-width="2in"
                                                 scaling="non-uniform"
                                                 font-size="0pt"
                                                 padding="0mm"
                                                 margin="0mm"
                                                 src="url(https://files.fm/thumb_show.php?i=wfacsrkxg)"
                            />
                        </fo:block>
                        <fo:block text-align="center" font-size="22px" font-family="SourceSansPro">
                            ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ <fo:inline font-weight="bold">COVID-19</fo:inline>
                        </fo:block>
                        <fo:block text-align="center" font-size="16px" color="#696969" font-family="SourceSansPro">
                            POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV <fo:inline font-weight="bold">COVID-19</fo:inline>
                        </fo:block>
                        <fo:block text-align="center" font-size="16px" color="#696969" font-family="SourceSansPro">
                            CONFIRMATION OF THE <fo:inline font-weight="bold">COVID-19</fo:inline> VACCINATION
                        </fo:block>

                        <fo:block margin-top="7mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Име и презиме:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                    <xsl:value-of select="//za:ime" />
                                </fo:inline>
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="15mm">
                                    <xsl:value-of select="//za:prezime" />
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Ime i prezime / First and Last Name
                            </fo:block>
                        </fo:block>

                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Датум рођења:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="substring(//b:datum_rodjenja, 9, 2)"/>.<xsl:value-of
                                        select="substring(//b:datum_rodjenja, 6, 2)"/>.<xsl:value-of
                                        select="substring(//b:datum_rodjenja, 0, 5)"/>.
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Datum rođenja / Date Of Birth
                            </fo:block>
                        </fo:block>

                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Пол:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//za:pol" />
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Pol / Gender:
                                <fo:inline font-size="14px" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//za:pol" />
                                </fo:inline>
                            </fo:block>
                        </fo:block>

                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                ЈМБГ:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//za:jmbg" />
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                JMBG / Personal No.
                            </fo:block>
                        </fo:block>
                        <!--for each-->
                        <xsl:for-each select="//b:doza">
                            <fo:block margin-top="3mm">
                                <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                    Датум давања и број серије
                                    <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                        <xsl:value-of select="za:broj_doze"/>.
                                    </fo:inline>
                                    дозе:
                                    <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                        <xsl:value-of select="substring(za:datum_davanja, 9, 2)"/>.<xsl:value-of
                                            select="substring(za:datum_davanja, 6, 2)"/>.<xsl:value-of
                                            select="substring(za:datum_davanja, 0, 5)"/>,
                                        <xsl:value-of select="za:broj_serije"/>
                                    </fo:inline>
                                </fo:block>
                                <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                    Datum vakcinacije
                                    <fo:inline font-family="SourceSansPro" >
                                        <xsl:value-of select="za:broj_doze"/>.
                                    </fo:inline>
                                    doze / Vaccination Date of
                                    <fo:inline font-family="SourceSansPro" >
                                        <xsl:value-of select="za:broj_doze"/>.
                                    </fo:inline>
                                    Dose
                                </fo:block>
                            </fo:block>
                        </xsl:for-each>



                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Здравствена установа која вакцинише:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//b:ustanova"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Zdravstvena ustanova koja vakciniše / Health care institution of
                                vaccination
                            </fo:block>
                        </fo:block>

                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Назив вакцине:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//b:naziv_vakcine"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Naziv vakcine / Name of vaccine
                            </fo:block>
                        </fo:block>



                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Датум издавања потврде:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="substring(//b:datum_izdavanja, 9, 2)"/>.<xsl:value-of
                                        select="substring(//b:datum_izdavanja, 6, 2)"/>.<xsl:value-of
                                        select="substring(//b:datum_izdavanja, 0, 5)"/>.
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Datum izdavanja potvrde / Confirmation Release Date
                            </fo:block>
                        </fo:block>

                        <!--ovo desno-->
                        <fo:block margin-top="3mm" text-align="right">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Здравствена установа:
                                <fo:inline font-size="14px" font-weight="bold" font-family="SourceSansPro" margin-left="5mm">
                                    <xsl:value-of select="//b:ustanova"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Zdravstvena ustanova / Medical institution
                            </fo:block>
                        </fo:block>


                        <!--ovo desno-->
                        <fo:block margin-top="3mm" text-align="right">
                            <fo:external-graphic content-height="scale-to-fit" height="2in"  content-width="2in" scaling="non-uniform" font-size="0pt" padding="0mm" margin="0mm">
                                <xsl:attribute name="src">
                                    <xsl:value-of select="//b:qr_kod" />
                                </xsl:attribute>
                            </fo:external-graphic>
                        </fo:block>


                        <fo:block margin-top="3mm">
                            <fo:block font-size="14px" font-weight="bold" font-family="SourceSansPro">
                                Ова потврда важи без потписа и печата
                            </fo:block>
                            <fo:block font-size="12px" color="#696969" font-family="SourceSansPro">
                                Ova potvrda važi bez potpisa i pečata / Тhis certificate is valid
                                without signatures and seals
                            </fo:block>
                        </fo:block>


                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>