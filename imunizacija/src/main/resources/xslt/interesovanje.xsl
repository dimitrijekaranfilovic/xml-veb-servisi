<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/interesovanje">
    <xsl:template match="/">
        <html>
            <head>
                <title>Interesovanje</title>
                <style>
                    .indent {
                    padding-left: 30px;
                    }

                    .ruler {
                    border-top: 2px dotted;
                    }

                    .circle {
                    width: 350px;
                    line-height: 30px;
                    border-radius: 50%;
                    border: 3px solid #000;
                    padding-left: 30px;
                    }

                    .circle-yes-no {
                    width: 30px;
                    line-height: 30px;
                    border-radius: 50%;
                    border: 3px solid #000;
                    padding-left: 1px;
                    margin-left: 25px;
                    }

                    .potpis {
                    float: right;
                    border-top: 2px solid black;
                    margin-right: 100px;
                    padding-left: 50px;
                    padding-right: 50px;
                    }

                    .datum {
                    margin-left: 100px;
                    }

                    .underlined {
                    border-bottom: 1px solid black;
                    }
                </style>
            </head>
            <body>
                <h1 align="center">
                    Исказивање интересовања за вакцинисање против COVID-19
                </h1>
                <p>Одаберите опцију:</p>
                <xsl:variable name="x" select="//b:drzavljanstvo"/>
                <p class="indent">
                    <xsl:choose>
                        <xsl:when test="$x = 'DRZAVLJANIN_REPUBLIKE_SRBIJE'">
                            <xsl:attribute name="class">circle</xsl:attribute>
                        </xsl:when>
                    </xsl:choose>
                    Држављанин Републике Србије
                </p>
                <p class="indent">
                    <xsl:choose>
                        <xsl:when test="$x = 'STRANI_DRZAVLJANIN_SA_BORAVKOM_U_RS'">
                            <xsl:attribute name="class">circle</xsl:attribute>
                        </xsl:when>
                    </xsl:choose>
                    Страни држављанин са боравком у РС
                </p>
                <p class="indent">
                    <xsl:choose>
                        <xsl:when test="$x = 'STRANI_DRZAVLJANIN_BEZ_BORAVKA_U_RS'">
                            <xsl:attribute name="class">circle</xsl:attribute>
                        </xsl:when>
                    </xsl:choose>
                    Страни држављанин без боравка у РС
                </p>
                <div>ЈМБГ:</div>
                <div>
                    <xsl:value-of select="//b:jmbg"/>
                </div>
                <hr class="ruler"/>
                <div>Име:</div>
                <div>
                    <xsl:value-of select="//za:ime"/>
                </div>
                <hr class="ruler"/>
                <div>Презиме:</div>
                <div>
                    <xsl:value-of select="//za:prezime"/>
                </div>
                <hr class="ruler"/>
                <div>Адреса електронске поште:</div>
                <xsl:value-of select="//za:email"/>
                <hr class="ruler"/>
                <div
                >Број мобилног телефона (навести број у формату 06X..... без размака и
                    цртица):
                </div
                >
                <div>
                    <xsl:value-of select="//za:broj_mobilnog"/>
                </div>
                <hr class="ruler"/>
                <div>
                    Број фиксног телефона (навести број у формату нпр. 011..... без размака и
                    цртица):
                </div>
                <div>
                    <xsl:value-of select="//za:broj_fiksnog"/>
                </div>
                <hr class="ruler"/>
                <div>
                    Одаберите локацију где желите да примите вакцину (унесите општину):

                </div>
                <div>
                    <xsl:value-of select="//b:odabrana_lokacija_primanja_vakcine"/>
                </div>
                <hr class="ruler"/>
                <br/>
                <p>
                    Исказујем интересовање да примим искључиво вакцину следећих произвођача за
                    који Агенција за лекове и медицинска средства потврди безбедност,
                    ефикасност и квалитет и изда дозволу за употребу лека:
                </p>
                <xsl:for-each select="//b:proizvodjac">
                    <p class="indent">
                        <xsl:value-of select="text()"/>
                    </p>
                </xsl:for-each>
                <p>Да ли сте добровољни давалац крви?</p>
                <xsl:variable name="davalac_krvi" select="//b:davalac_krvi"/>
                <p class="indent">
                    <xsl:choose>
                        <xsl:when test="$davalac_krvi = 'true'">
                            <xsl:attribute name="class">circle-yes-no</xsl:attribute>
                        </xsl:when>
                    </xsl:choose>
                    Да
                </p>
                <p class="indent">
                    <xsl:choose>
                        <xsl:when test="$davalac_krvi = 'false'">
                            <xsl:attribute name="class">circle-yes-no</xsl:attribute>
                        </xsl:when>
                    </xsl:choose>
                    Не
                </p>

                <br/>
                <br/>

                <span class="datum"
                >дана
                    <span class="underlined"><xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of
                            select="substring(//b:datum, 6, 2)"/>.
                    </span>
                    20
                    <span class="underlined"
                    >
                        <xsl:value-of select="substring(//b:datum, 3, 2)"/>.
                    </span
                    >
                    године
                </span
                >
                <span class="potpis">Потпис</span>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>