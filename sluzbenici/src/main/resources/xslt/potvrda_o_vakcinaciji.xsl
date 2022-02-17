<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/potvrda_o_vakcinaciji">
    <xsl:template match="/">
        <html>
            <style>
                .left {
                    text-align: left;
                }
                .right {
                    text-align: right;
                }

                .center {
                    text-align: center;
                }

                .title {
                    font-weight: bold;
                    font-size: 30px;
                }

                .subtitle {
                    font-size: 16px;
                    color: rgb(105, 105, 105);
                }

                .data-cyrillic {
                    font-weight: bold;
                    font-size: 14px;
                }

                .data-other {
                    font-size: 12px;
                    color: rgb(105, 105, 105);
                }

                .img-right {
                    float: right;
                }

                .container {
                    margin-bottom: 100px;
                }
                #last-name{
                    margin-left: 4px;
                }

            </style>
            <body>
                <div class="container">
                    <div>
                        <img
                            alt="logo"
                            class="batut-logo"
                            src="https://files.fm/thumb_show.php?i=wfacsrkxg"
                        />
                    </div>
                    <div>
                        <p class="data-cyrillic">
                            Шифра потврде вакцинације:
                            <span>
                                <xsl:value-of select="//b:sifra_potvrde" />
                            </span>
                        </p>
                        <p class="data-other">Šifra potvrde / Confirmation code:</p>
                    </div>

                    <div>
                        <p class="center title">ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</p>
                        <p class="center subtitle">
                            POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV <strong> COVID-19 </strong>
                        </p>
                        <p class="center subtitle">
                            CONFIRMATION OF THE <strong> COVID-19 </strong> VACCINATION
                        </p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            Име и презиме:
                            <span>
                                <xsl:value-of select="//za:ime" />
                                <span id="last-name">
                                    <xsl:value-of select="//za:prezime" />
                                </span>
                            </span>
                        </p>
                        <p class="data-other">Ime i prezime / First and Last Name</p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            Датум рођења:
                            <span>
                                <xsl:value-of select="substring(//b:datum_rodjenja, 0, 11)"/>
                            </span>
                        </p>
                        <p class="data-other">Datum rođenja / Date Of Birth</p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            Пол:
                            <span>
                                <xsl:value-of select="//za:pol" />
                            </span>
                        </p>
                        <p class="data-other">
                            Pol / Gender:
                            <span>
                                <xsl:value-of select="//za:pol" />
                            </span>
                        </p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            ЈМБГ:
                            <span>
                                <xsl:value-of select="//za:jmbg" />
                            </span>
                        </p>
                        <p class="data-other">JMBG / Personal No.</p>
                    </div>

                    <!--for each-->
                    <xsl:for-each select="//b:doza">
                        <div>
                            <p class="data-cyrillic">
                                Датум давања и број серије <xsl:value-of select="za:broj_doze"/>. дозе:
                                <span>
                                    <xsl:value-of select="substring(za:datum_davanja, 0, 11)"/>,
                                    <xsl:value-of select="za:broj_serije"/>
                                </span>
                            </p>
                            <p class="data-other">
                                Datum vakcinacije <xsl:value-of select="za:broj_doze"/>. doze / Vaccination Date of <xsl:value-of select="za:broj_doze"/>. Dose
                            </p>
                        </div>
                    </xsl:for-each>


                    <div>
                        <p class="data-cyrillic">
                            Здравствена установа која вакцинише:
                            <span>
                                <xsl:value-of select="//b:ustanova"/>
                            </span>
                        </p>
                        <p class="data-other">
                            Zdravstvena ustanova koja vakciniše / Health care institution of
                            vaccination
                        </p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            Назив вакцине:
                            <span>
                                <xsl:value-of select="//b:naziv_vakcine"/>
                            </span>
                        </p>
                        <p class="data-other">Naziv vakcine / Name of vaccine</p>
                    </div>

                    <div>
                        <p class="data-cyrillic">
                            Датум издавања потврде:
                            <span>
                                <xsl:value-of select="substring(//b:datum_izdavanja, 0, 11)"/>
                            </span>
                        </p>
                        <p class="data-other">
                            Datum izdavanja potvrde / Confirmation Release Date
                        </p>
                    </div>

                    <div>
                        <p class="data-cyrillic right">
                            Здравствена установа:
                            <span>
                                <xsl:value-of select="//b:ustanova"/>
                            </span>
                        </p>
                        <p class="data-other right">
                            Zdravstvena ustanova / Medical institution
                        </p>
                    </div>
                    <img class="img-right" alt="qr-kod">
                        <xsl:attribute name="src">
                            <xsl:value-of select="//b:qr_kod"/>
                        </xsl:attribute>
                    </img>

                    <div>
                        <p class="data-cyrillic">Ова потврда важи без потписа и печата</p>
                        <p class="data-other">
                            Ova potvrda važi bez potpisa i pečata / Тhis certificate is valid
                            without signatures and seals
                        </p>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>