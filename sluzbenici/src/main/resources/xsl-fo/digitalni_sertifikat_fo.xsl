<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/digitalni_sertifikat">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="digitalni-sertifikat-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="digitalni-sertifikat-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="60%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="left">
                                        <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                                            <fo:external-graphic content-height="scale-to-fit" height="2in"  content-width="2in" scaling="non-uniform" font-size="0pt" padding="0mm" margin="0mm" src="data:image/png;base64,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" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block linefeed-treatment="preserve" font-size="12px" font-family="Arial" padding="1px">
                                            <fo:inline font-size="16px" font-weight="bold"> ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ </fo:inline>
                                            Потврда о извршеној вакцинацији против
                                            COVID-19 и резултатима тестирања
                                            <fo:inline font-size="16px" font-weight="bold" margin-top="10px"> DIGITAL GREEN CERTIFICATE </fo:inline>
                                            Certificate of vaccination against COVID-19
                                            and test results
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                                            <fo:external-graphic content-height="scale-to-fit" height="2in"  content-width="2in" scaling="non-uniform" font-size="0pt" padding="0mm" margin="0mm">
                                                <xsl:attribute name="src">
                                                    <xsl:value-of select="//b:qr_kod" />
                                                </xsl:attribute>
                                            </fo:external-graphic>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block>
                        <fo:table>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="35%"/>
                            <fo:table-column column-width="15%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell font-family="Arial" font-weight="bold" text-align="left">
                                        <fo:block>
                                            <fo:inline font-weight="bold"> Број сертификата / </fo:inline>
                                            Certificate ID:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left"><fo:block><xsl:value-of select="//b:broj_sertifikata" /></fo:block> </fo:table-cell>

                                    <fo:table-cell font-weight="bold" text-align="left">
                                        <fo:block font-family="Arial">
                                            Датум и време издавања сертификата /
                                            Certificate issuing date and time:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right"><fo:block><xsl:value-of select="//b:datum_vreme_izdavanja" /></fo:block> </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block>
                        <fo:table border-bottom="1px solid black" border-collapse="separate" border-separation="6pt">
                            <fo:table-column column-width="40%" />
                            <fo:table-column column-width="60%" />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="left">
                                        <fo:block font-family="Arial" font-weight="bold">
                                            <fo:inline font-family="Arial" font-weight="bold">Име и презиме / Name and surname: </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block font-family="Arial">
                                            <xsl:value-of select="//za:ime" /> <xsl:text> </xsl:text> <xsl:value-of select="//za:prezime" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell font-family="Arial" font-weight="bold" text-align="left">
                                        <fo:block>Пол / Gender:</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block font-family="Arial">
                                            <xsl:value-of select="//za:pol" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell font-family="Arial" font-weight="bold" text-align="left">
                                        <fo:block>Датум рођења / Date of birth:</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="substring(//b:datum_rodjenja, 0, 11)"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell font-family="Arial" font-weight="bold" text-align="left">
                                        <fo:block>ЈМБГ / Personal No. / EBS</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="//za:jmbg" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell font-family="Arial" font-weight="bold" text-align="left">
                                        <fo:block>Број пасоша / Passport No.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <xsl:value-of select="//b:broj_pasosa" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block font-family="Arial" font-size="12px" text-align="center">
                        Вакцинација / Vaccination
                    </fo:block>

                    <fo:block>
                        <fo:table>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:table border-collapse="separate" border-separation="6pt">
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Доза / Dose: 1/2</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Тип / Type:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=1]/za:tip" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Произвођач и серија / Manufacturer and batch number:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=1]/za:proizvodjac" />
                                                                <xsl:text>, </xsl:text>
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=1]/za:broj_serije" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Датум / Date: <xsl:value-of select="//b:doza_vakcine[za:broj_doze=1]/za:datum_davanja" /></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Здравствена установа / Health care institution:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=1]/b:zdravstvena_ustanova" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:table border-collapse="separate" border-separation="6pt">
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Доза / Dose: 2/2</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Тип / Type:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=2]/za:tip" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Произвођач и серија / Manufacturer and batch number:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=2]/za:proizvodjac" />
                                                                <xsl:text>, </xsl:text>
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=2]/za:broj_serije" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Датум / Date: <xsl:value-of select="//b:doza_vakcine[za:broj_doze=2]/za:datum_davanja" /></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">Здравствена установа / Health care institution:</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                <xsl:value-of select="//b:doza_vakcine[za:broj_doze=2]/b:zdravstvena_ustanova" />
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block>
                        <fo:table border-top="1px solid black">
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:table border-right="1px solid black">
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                SARS-CoV-2 RT
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-bottom="1px solid black">
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                Real-time PCR
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Врста узорка / Sample type:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Произвођач теста / Test manufacturer:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време узорковања / Date and time of sampling:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време издавања резултата / Date and time of result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-top="1px solid black" background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Резултат / Result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Лабораторија / Laboratory:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:table border-right="1px solid black">
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                SARS-CoV-2 Ag-RDT
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-bottom="1px solid black">
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                (Antigen Rapid Detection test)
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Врста узорка / Sample type:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Произвођач теста / Test manufacturer:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време узорковања / Date and time of sampling:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време издавања резултата / Date and time of result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-top="1px solid black" background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Резултат / Result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Лабораторија / Laboratory:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:table>
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                SARS-CoV-2 RBD S-Protein
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-bottom="1px solid black">
                                                        <fo:table-cell>
                                                            <fo:block text-align="center">
                                                                Immunoglobulin G (IgG) test
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Врста узорка / Sample type:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Произвођач теста / Test manufacturer:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време узорковања / Date and time of sampling:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Датум и време издавања резултата / Date and time of result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-top="1px solid black" background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Резултат / Result:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block font-family="Arial">
                                                                Лабораторија / Laboratory:
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row background-color="lightgray">
                                                        <fo:table-cell>
                                                            <fo:block>
                                                                N / A
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block font-family="Arial" text-align="right" font-size="10px">
                        Дигитални потпис / Digitally signed by:
                    </fo:block>

                    <fo:block>
                        <fo:table>
                            <fo:table-column column-width="20%" />
                            <fo:table-column column-width="30%" />
                            <fo:table-column column-width="20%" />
                            <fo:table-column column-width="30%" />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                                            <fo:external-graphic content-height="scale-to-fit" height="1in"  content-width="1in" scaling="non-uniform" font-size="0pt" padding="0mm" margin="0mm" src="url(https://euprava.gov.rs/media/logos/Batut.gif)" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block linefeed-treatment="preserve" text-align="left" font-size="10px" font-family="Arial" padding="1px">
                                            Сертификат издаје:
                                            Институт за Јавно здравље Србије
                                            "Др Милан Јовановић Батут"
                                            Certificate issued by:
                                            Institute of Public Health of Serbia
                                            "Dr Milan Jovanovic Batut"
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                                            <fo:external-graphic content-height="scale-to-fit" height="1in"  content-width="1in" scaling="non-uniform" font-size="0pt" padding="0mm" margin="0mm" src="data:image/png;base64,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" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block linefeed-treatment="preserve" text-align="left" font-size="10px" font-family="Arial" padding="1px">
                                            РЕПУБЛИКА СРБИЈА
                                            Влада Републике Србије
                                            Канцеларија за информационе
                                            технологије и електронску управу
                                            Немањина 11, БЕОГРАД
                                            Датум: <xsl:value-of select="substring(//b:datum_vreme_izdavanja, 0, 11)"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>