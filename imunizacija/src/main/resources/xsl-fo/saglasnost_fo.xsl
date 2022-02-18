<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/saglasnost">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="saglasnost-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="saglasnost-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:float float="end">
                            <fo:block top="0mm" left="0mm" padding="0mm" margin="0mm" line-height="0mm" font-size="0pt">
                                <fo:external-graphic font-size="0pt" padding="0mm" margin="0mm" content-width="90mm"
                                                     height="150mm"
                                                     src="url(https://files.fm/thumb_show.php?i=wfacsrkxg)"/>
                            </fo:block>
                        </fo:float>

                        <fo:block linefeed-treatment="preserve" font-family="SourceSansPro" font-size="20px"
                                  font-weight="bold" padding="1px">
                            САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ
                            ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ
                        </fo:block>

                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="15px" font-weight="normal">
                        (попуњава пацијент)
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="10mm">
                        <fo:table>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="40%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block font-weight="bold">Држављанство</fo:block>
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
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Презиме: </fo:inline>
                                            <xsl:value-of select="//b:puno_ime/za:prezime"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Име: </fo:inline>
                                            <xsl:value-of select="//b:puno_ime/za:ime"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Име родитеља: </fo:inline>
                                            <xsl:value-of select="//b:puno_ime/b:ime_roditelja"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="40%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="40%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:variable name="x" select="//b:pol"/>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Pol:</fo:inline>
                                            <xsl:choose>
                                                <xsl:when test="$x = 'MUSKI'">
                                                    ☑ М ☐ Ж
                                                </xsl:when>
                                                <xsl:when test="$x = 'ZENSKI'">
                                                    ☐ М ☑ Ж
                                                </xsl:when>
                                            </xsl:choose>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Датум рођења: </fo:inline>
                                            <xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of
                                                select="substring(//b:datum, 6, 2)"/>.<xsl:value-of
                                                select="substring(//b:datum, 0, 5)"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Место рођења:</fo:inline>
                                            <xsl:value-of select="//b:mesto_rodjenja"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="70%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Адреса(улица и број): </fo:inline>
                                            <xsl:value-of
                                                    select="//b:ulica"/>,
                                            <xsl:value-of select="//b:broj_zgrade"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Место/Насеље: </fo:inline>
                                            <xsl:value-of select="//b:mesto"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="35%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Општина/град: </fo:inline>
                                            <xsl:value-of select="//b:opstina"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Тел. фиксни: </fo:inline>
                                            <xsl:value-of select="//za:broj_fiksnog"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="35%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="40%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Тел. мобилни: </fo:inline>
                                            <xsl:value-of select="//za:broj_mobilnog"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Имејл: </fo:inline>
                                            <xsl:value-of select="//za:email"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <xsl:variable name="x" select="//b:radni_status"/>
                        <fo:inline font-weight="bold">Радни статус: </fo:inline>
                        <xsl:choose>
                            <xsl:when test="$x = 'ZAPOSLEN'">
                                <fo:inline> ☑ запослен</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'ZAPOSLEN'">
                                <fo:inline> ☐ запослен</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'NEZAPOSLEN'">
                                <fo:inline> ☑ незапослен</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'NEZAPOSLEN'">
                                <fo:inline> ☐ незапослен</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'PENZIONER'">
                                <fo:inline> ☑ пензионер</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'PENZIONER'">
                                <fo:inline> ☐ пензионер</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'UCENIK'">
                                <fo:inline> ☑ ученик</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'UCENIK'">
                                <fo:inline> ☐ ученик</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'STUDENT'">
                                <fo:inline> ☑ студент</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'STUDENT'">
                                <fo:inline> ☐ студент</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'DETE'">
                                <fo:inline> ☑ дете</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'DETE'">
                                <fo:inline> ☐ дете</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <xsl:variable name="x" select="//b:zanimanje_zaposlenog"/>
                        <fo:inline font-weight="bold">Занимање запосленог: </fo:inline>
                        <xsl:choose>
                            <xsl:when test="$x = 'ZDRAVSTVENA_ZASTITA'">
                                <fo:inline> ☑ здравствена заштита</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'ZDRAVSTVENA_ZASTITA'">
                                <fo:inline> ☐ здравствена заштита</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'SOCIJALNA_ZASTITA'">
                                <fo:inline> ☑ социјална заштита</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'SOCIJALNA_ZASTITA'">
                                <fo:inline> ☐ социјална заштита</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'PROSVETA'">
                                <fo:inline> ☑ просвета</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'PROSVETA'">
                                <fo:inline> ☐ просвета</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'MUP'">
                                <fo:inline> ☑ МУП</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'MUP'">
                                <fo:inline> ☐ МУП</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'VOJSKA'">
                                <fo:inline> ☑ Војска РС</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'VOJSKA'">
                                <fo:inline> ☐ Војска РС</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                        <xsl:choose>
                            <xsl:when test="$x = 'DRUGO'">
                                <fo:inline> ☑ друго</fo:inline>
                            </xsl:when>
                            <xsl:when test="$x != 'DRUGO'">
                                <fo:inline> ☐ друго</fo:inline>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="55%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="70%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:variable name="x" select="//b:korisnik_ustanove_socijalne_zastite"/>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Корисник установе соц. заштите: </fo:inline>
                                            <xsl:choose>
                                                <xsl:when test="$x = 'true'">
                                                    <fo:inline>☑ ДА</fo:inline>
                                                    <fo:inline><xsl:text> </xsl:text></fo:inline>
                                                    <fo:inline>☐ НЕ</fo:inline>
                                                </xsl:when>
                                                <xsl:when test="$x = 'false'">
                                                    <fo:inline>☐ ДА</fo:inline>
                                                    <fo:inline><xsl:text> </xsl:text></fo:inline>
                                                    <fo:inline>☑ НЕ</fo:inline>
                                                </xsl:when>
                                            </xsl:choose>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Назив и општина седишта: </fo:inline>
                                            <xsl:value-of select="//b:naziv_sedista"/>
                                            <xsl:value-of select="//b:opstina_sedista"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="150%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <xsl:variable name="x" select="//b:izjava"/>
                                        <fo:block>
                                            <fo:inline font-weight="bold">Изјављујем да: </fo:inline>
                                            <xsl:choose>
                                                <xsl:when test="$x = 'true'">
                                                    <fo:inline font-weight="bold">САГЛАСАН САМ</fo:inline>
                                                </xsl:when>
                                                <xsl:when test="$x = 'false'">
                                                    <fo:inline font-weight="bold">НИСАМ САГЛАСАН</fo:inline>
                                                </xsl:when>
                                            </xsl:choose>
                                            са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека):
                                            <xsl:value-of select="//b:naziv_imunoloskog_leka"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="150%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block font-weight="bold">Лекар ми је објаснио предности и ризике од
                                            спровођења активне/пасивне имунизације наведеним имунолошким леком.
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-left="500px">
                        <fo:float float="end">
                            <fo:block font-family="SourceSansPro">
                                Датум:
                            </fo:block>
                            <fo:block font-family="SourceSansPro">
                                <xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of
                                    select="substring(//b:datum, 6, 2)"/>.<xsl:value-of
                                    select="substring(//b:datum, 0, 5)"/>
                            </fo:block>
                        </fo:float>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" margin-top="5px" font-weight="bold">
                        Потпис пацијента или законског заступника пацијента:
                    </fo:block>
                    <fo:block margin-top="15px" margin-right="350px" font-family="SourceSansPro" border-bottom="solid"
                              border-bottom-width="0.2mm">

                    </fo:block>
                    <fo:block>
                        <fo:leader leader-pattern="rule" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                    </fo:block>
                    <fo:block linefeed-treatment="preserve" font-family="SourceSansPro" font-size="15px"
                              font-weight="normal" padding="1px" text-align="center">
                        ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" text-align="center">
                        (попуњава здравствени радник)
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="5mm">
                        <fo:table>
                            <fo:table-column column-width="65%"/>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="70%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>Здравствена установа:
                                            <xsl:value-of select="//b:naziv"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>|</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>Пункт:
                                            <xsl:value-of select="//b:punkt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        <fo:table>
                            <fo:table-column column-width="150%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell vertical-align="middle">
                                        <fo:block>Име, презиме, факсимил и бр. телефона лекара: <xsl:value-of
                                                select="//b:lekar/za:ime"/>, <xsl:value-of
                                                select="//b:lekar/za:prezime"/>,
                                            <xsl:value-of select="//b:broj_mobilnog"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal" margin-top="2mm">
                        Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама
                        после
                        вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке
                        унети у лични
                        картон о извршеним имунизацијама и здравствени картон.
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="10px" font-weight="normal" margin-top="2mm"
                              border="1px">
                        <fo:table>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-column column-width="12%" border="1px solid darkgrey"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell>
                                        <fo:block>Назив вакцине</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Датум давања вакцине</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Начин давања вакцине</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Екстремитет</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Серија вакцине (лот)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Произвођач</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Нежељена реакција</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Потпис лекара</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="//b:vakcina">
                                    <fo:table-row border="1px solid darkgrey">
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:value-of select="za:broj_doze"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:value-of select="za:datum_davanja"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>ИМ</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>1) ДР, 2) ЛР</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:value-of select="za:broj_serije"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:value-of select="za:tip"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:value-of select="b:nuspojava"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell number-columns-spanned="2">
                                        <fo:block>Привремене контраиндикације (датум утврђивања и дијагноза):</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="6">
                                        <fo:block>
                                            <xsl:for-each select="//b:kontraindikacija">
                                                <xsl:value-of select="b:dijagnoza"/>,
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell number-columns-spanned="2">
                                        <fo:block>Одлука комисије за трајне контраиндикације (ако постоји, уписати
                                            Да):
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="6">
                                        <fo:block>
                                            <xsl:variable name="x"
                                                          select="//b:odluka_komisije_za_trajne_kontraindikacije"/>
                                            <xsl:choose>
                                                <xsl:when test="$x = 'true'">
                                                    <fo:inline>ДА</fo:inline>
                                                </xsl:when>
                                                <xsl:when test="$x = 'false'">
                                                    <fo:inline>НЕ</fo:inline>
                                                </xsl:when>
                                            </xsl:choose>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block font-family="SourceSansPro" font-size="11px" font-weight="normal">
                        <fo:inline font-weight="bold">Напомена:</fo:inline>
                        Образац се чува као део медицинске документације пацијента.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>