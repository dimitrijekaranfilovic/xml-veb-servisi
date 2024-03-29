<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/izvestaj_o_imunizaciji">
    <xsl:template match="/">
        <html>
            <style>
                .container {
                    margin-left: 7%;
                    margin-right: 10%;
                }

                .right {
                    text-align: right;
                }

                .left {
                    text-align: left;
                }

                .header {
                    font-weight: bold;
                    font-size: 30px;
                }
                .table {
                    width: 100%;
                }

                .table, .table th, .table td {
                    border: 1px solid black;
                    border-collapse: collapse;
                }

                .table td {
                    text-align: center;
                    vertical-align: middle;
                }

                .center {
                    text-align: center;
                }
            </style>
            <body>
                <div class="container">
                    <p class="header center">
                       Извештај о имунизацији
                    </p>
                    <p>
                        Извештај се односи на период од
                        <!--vidi za formatiranje datuma-->
                        <strong>
                            <xsl:value-of select="substring(//b:period_od, 9, 2)"/>.<xsl:value-of
                                select="substring(//b:period_od, 6, 2)"/>.<xsl:value-of
                                select="substring(//b:period_od, 0, 5)"/>.
                        </strong>
                        до
                        <strong>
                            <xsl:value-of select="substring(//b:period_do, 9, 2)"/>.<xsl:value-of
                                select="substring(//b:period_do, 6, 2)"/>.<xsl:value-of
                                select="substring(//b:period_do, 0, 5)"/>.
                        </strong>
                    </p>

                    <p>
                        У напоменутом временском интервалу је:
                        <ul>
                            <li>
                                поднето
                                <strong>
                                    <xsl:value-of select="//b:podneto_dokumenata_o_interesovanju" />
                                </strong>
                                докумената о интересовању за имунизацију
                            </li>
                            <li>
                                примљено

                                <strong>
                                    <xsl:value-of select="//b:primljeno_zahteva_za_sertifikat" />
                                </strong>

                                захтева за дигитални сертификат,
                                од којих је

                                <strong>
                                    <xsl:value-of select="//b:izdato_zahteva_za_sertifikat" />
                                </strong>
                                издато.
                            </li>
                        </ul>
                    </p>

                    <p>
                        Дато је
                        <strong>
                            <xsl:value-of select="sum(//b:data_doza/b:broj_datih_doza)"/>
                        </strong>

                        доза вакцине против COVID-19 вируса у
                        следећој количини:
                        <table class="table">
                            <thead>
                                <th>Редни број вакцине</th>
                                <th>Број датих доза</th>
                            </thead>
                            <tbody>
                                <xsl:for-each select="//b:data_doza">
                                    <tr>
                                        <td>
                                            <strong>
                                                <xsl:value-of select="b:redni_broj"/>
                                            </strong>
                                        </td>
                                        <td>
                                            <xsl:value-of select="b:broj_datih_doza"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </tbody>
                        </table>
                    </p>
                    <p>
                        Расподела по произвођачима:
                        <ul>
                            <xsl:for-each select="//b:stavka">
                                <li>
                                    <strong>
                                        <xsl:value-of select="b:proizvodjac"/>-<xsl:value-of select="b:broj_doza"/>
                                    </strong>
                                    доза
                                </li>
                            </xsl:for-each>
                        </ul>
                    </p>
                    <table border="0" width="100%">
                        <tbody>
                            <tr>
                                <td class="left">
                                    Датум издавања:
                                    <u>
                                        <xsl:value-of select="substring(//b:datum_izdavanja, 9, 2)"/>.<xsl:value-of
                                            select="substring(//b:datum_izdavanja, 6, 2)"/>.<xsl:value-of
                                            select="substring(//b:datum_izdavanja, 0, 5)"/>.
                                    </u>
                                </td>

                                <td class="center">
                                    _______________________________
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td class="center">Потпис</td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
