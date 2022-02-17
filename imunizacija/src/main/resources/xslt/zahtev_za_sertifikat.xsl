<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/zahtev_za_sertifikat">
    <xsl:template match="/">
        <html>
            <head>
                <title>Захтев за сертификат</title>
                <style>
                    body{
                    font-size: 22px;
                    }

                    .text-center{
                    text-align:center;
                    }
                    .dotted{
                    border-bottom:3px dotted black;
                    }

                    .underlined{
                    border-bottom: 1px solid;
                    }

                    /* Clear floats after the columns */
                    .row{
                    display: flex;
                    margin-bottom: 4px;

                    }

                    p{
                    text-align: justify;
                    text-justify: inter-word;
                    }

                    .column-left {
                    margin-right: 5px;
                    }

                    .column-rigth {
                    flex: 1;
                    width:auto;
                    padding-left: 5px;
                    text-decoration-style: dotted;
                    }

                    .potpis {
                    float: right;
                    border-top: 2px solid black;
                    margin-right: 100px;
                    padding-left: 50px;
                    padding-right: 50px;
                    }

                    .heading{
                    font-weight: bold;
                    font-size: 35px;
                    }

                    .subheading{
                    font-weight: bold;
                    font-size: 30px;
                    }

                    .indented-paragraph{
                    text-indent:50px;
                    text-align=justified;
                    }

                </style>
            </head>
            <body>

                <div class="text-center heading">ЗАХТЕВ</div>
                <div class="text-center subheading">за издавање дигиталног зеленог сертификата</div>

                <p class="indented-paragraph">У складу са одредбом Републике Србије о издавању дигиталног зеленог
                    сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима тестирања на заразну
                    болест SARS-CoV-2 или опоравку од болести COVID-19, подносим захтев за издавање дигиталног зеленог
                    сертификата.
                </p>
                <br/>
                <br/>
                <div class="row">
                    <div class="column-left">Подносилац захтева:</div>
                </div>
                <div class="row">
                    <div class="column-left">Име и презиме:</div>
                    <div class="column-rigth dotted">
                        <xsl:value-of select="//za:ime"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="//za:prezime"/>
                    </div>
                </div>
                <div class="row">
                    <div class="column-left">Датум рођења:</div>
                    <div class="column-rigth dotted">
                        <xsl:value-of select="substring(//b:datum_rodjenja, 0, 11)"/>
                    </div>
                </div>
                <xsl:variable name="x" select="//za:pol"/>
                <div class="row">
                    <div class="column-left">Пол:</div>
                    <div class="column-rigth dotted">
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
                    </div>
                </div>
                <div class="row">
                    <xsl:variable name="x" select="//za:jmbg"/>
                    <div class="column-left">Јединствени матични број грађанина:</div>
                    <div class="column-rigth dotted">
                        <xsl:choose>
                            <xsl:when test="$x = ''">
                                Није унесен ЈМБГ
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x != ''">
                                <xsl:value-of select="//za:jmbg"></xsl:value-of>
                            </xsl:when>
                        </xsl:choose>
                    </div>
                </div>
                <div class="row">
                    <xsl:variable name="x" select="//b:broj_pasosa"/>
                    <div class="column-left">Број пасоша:</div>
                    <div class="column-rigth dotted">
                        <xsl:choose>
                            <xsl:when test="$x = ''">
                                Није унесен број пасоша
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x != ''">
                                <xsl:value-of select="//b:broj_pasosa"></xsl:value-of>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:value-of select="//b:broj_pasosa"></xsl:value-of>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="column-left">Разлог за подношење захтева:</div>
                </div>
                <div class="row">
                    <div class="column-rigth dotted">
                        <xsl:value-of select="//b:razlog_za_podnosenje_zahteva"
                                      disable-output-escaping="yes"> </xsl:value-of>

                    </div>
                </div>
                <div class="text-center">(навести што прецизнији разлог за подношење захтева за издавање дигиталног
                    пасоша)
                </div>
                <br/>

                <div class="row">
                    <div class="column-left">У</div>
                    <div class="underlined">
                        <xsl:value-of select="//b:mesto" disable-output-escaping="yes"> </xsl:value-of>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="column-left">дана</div>
                    <div class="column-left underlined">
                        <xsl:value-of select="substring(//b:datum, 9, 2)"/>.
                        <xsl:value-of select="substring(//b:datum, 6, 2)"/>.
                        <xsl:value-of select="substring(//b:datum, 0, 5)"/>.
                    </div>
                    <div class="column-right">године</div>
                </div>

                <span class="potpis">Потпис</span>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>