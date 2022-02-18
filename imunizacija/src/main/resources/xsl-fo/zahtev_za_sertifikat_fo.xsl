<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/zahtev_za_sertifikat"
                xmlns:d="data:,dpc"
>
    <xsl:import href="https://raw.githubusercontent.com/davidcarlisle/web-xslt/master/htmlparse/htmlparse.xsl"/>
    <xsl:template match="b:zahtev_za_sertifikat">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zahtev-za-sertifikat-page">
                    <fo:region-body margin="1.25in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zahtev-za-sertifikat-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="SourceSansPro" font-size="20px" font-weight="bold" text-align="center">
                        З А Х Т Е В
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="14px" font-weight="bold" text-align="center">
                        за издавање дигиталног зеленог сертификата
                    </fo:block>
                    <fo:block text-align="justify" start-indent="1.15cm" margin-top="10mm" font-family="SourceSansPro"
                              font-size="14px" font-weight="normal">
                        У складу са одредбом Републике Србије о издавању дигиталног зеленог сертификата
                    </fo:block>
                    <fo:block text-align="justify" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        као потврде о
                        извршеној вакцинацији против COVID-19, резултатима тестирања на
                        заразну
                        болест SARS-CoV-2 или опоравку од болести COVID-19, подносим захтев за издавање дигиталног
                        зеленог
                        сертификата.
                    </fo:block>

                    <fo:block margin-top="10mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Подносилац захтева:
                    </fo:block>
                    <fo:block margin-top="7mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Име и презиме:
                        <xsl:value-of select="//za:ime"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="//za:prezime"/>
                    </fo:block>
                    <fo:block margin-top="-4mm" margin-left="33.5mm">
                        <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                                   rule-thickness="1pt"/>
                    </fo:block>
                    <fo:block margin-top="1mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Датум рођења:
                        <xsl:value-of select="substring(//b:datum_rodjenja, 0, 11)"/>
                    </fo:block>
                    <fo:block margin-top="-4mm" margin-left="33mm">
                        <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                                   rule-thickness="1pt"/>
                    </fo:block>
                    <xsl:variable name="x" select="//za:pol"/>
                    <fo:block margin-top="1mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Пол:
                        <xsl:choose>
                            <xsl:when test="$x = 'MUSKI'">
                                Мушки
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'ZENSKI'">
                                Женски
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                    <fo:block margin-top="-4mm" margin-left="10.5mm">
                        <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                                   rule-thickness="1pt"/>
                    </fo:block>
                    <xsl:variable name="jmbg" select="//za:jmbg"/>
                    <fo:block margin-top="1mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Јединствени матични број грађанина:
                        <xsl:choose>
                            <xsl:when test="$jmbg = ''">
                                Није унесен ЈМБГ
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$jmbg != ''">
                                <xsl:value-of select="//za:jmbg"/>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                    <fo:block margin-top="-4mm" margin-left="84mm">
                        <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                                   rule-thickness="1pt"/>
                    </fo:block>
                    <xsl:variable name="broj_pasosa" select="//b:broj_pasosa"/>
                    <fo:block margin-top="1mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Број пасоша:
                        <xsl:choose>
                            <xsl:when test="$broj_pasosa = ''">
                                Није унесен број пасоша
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$broj_pasosa != ''">
                                <xsl:value-of select="//b:broj_pasosa"/>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                    <fo:block margin-top="-4mm" margin-left="28.5mm">
                        <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                                   rule-thickness="1pt"/>
                    </fo:block>
                    <fo:block margin-top="7mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        Разлог за подношење захтева:
                    </fo:block>
                    <fo:block font-size="13pt">
                        <xsl:apply-templates select="//b:razlog_za_podnosenje_zahteva"/>
                    </fo:block>
                    <fo:block margin-top="1mm" font-family="SourceSansPro" font-size="10px" font-weight="normal"
                              text-align="center">
                        (навести што прецизнији разлог за подношење захтева за издавање дигиталног
                        пасоша)
                    </fo:block>
                    <fo:block margin-top="20mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        У
                        <fo:inline border-bottom="solid" border-bottom-width="0.2mm">
                            <xsl:value-of select="//b:mesto"/>
                        </fo:inline>
                        ,
                    </fo:block>
                    <fo:block margin-top="8mm" font-family="SourceSansPro" font-size="14px" font-weight="normal">
                        дана
                        <fo:inline border-bottom="solid" border-bottom-width="0.2mm">
                            <xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of
                                select="substring(//b:datum, 6, 2)"/>.
                        </fo:inline>
                        20
                        <fo:inline border-bottom="solid" border-bottom-width="0.2mm">
                            <xsl:value-of select="substring(//b:datum, 3, 2)"/>.
                        </fo:inline>
                        године
                    </fo:block>
                    <fo:block margin-top="20"/>
                    <fo:block margin-top="20"/>
                    <fo:block>
                        <fo:float float="end">
                            <fo:block padding-left="20mm" padding-right="20mm" padding-top="2mm" margin-top="20"
                                      font-family="SourceSansPro"
                                      font-size="14px" font-weight="normal" border-top="solid" border-top-width="0.2mm">
                                Потпис
                            </fo:block>
                        </fo:float>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="//b:razlog_za_podnosenje_zahteva">
        <xsl:apply-templates select="d:htmlparse(., '', true())/node()"/>
    </xsl:template>

    <!-- HTML ENTITIES -->
    <xsl:template match="p">
        <xsl:message>Paragraph</xsl:message>
        <fo:block font-weight="normal">
            <xsl:apply-templates/>
            <fo:block margin-top="-4mm" margin-left="0mm">
                <fo:leader leader-pattern="dots" leader-length="100%" rule-style="solid"
                           rule-thickness="1pt"/>
            </fo:block>
        </fo:block>
    </xsl:template>
    <xsl:template match="strong">
        <xsl:message>Strong</xsl:message>
        <fo:inline font-weight="bold">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="em">
        <xsl:message>italic</xsl:message>
        <fo:inline font-style="italic">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="u">
        <xsl:message>underline</xsl:message>
        <fo:inline text-decoration="underline">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>
    <xsl:template match="s">
        <xsl:message>underline</xsl:message>
        <fo:inline text-decoration="line-through">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

</xsl:stylesheet>