<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:za="https://www.vakcinacija.rs/zajednicko"
xmlns:b="https://www.vakcinacija.rs/saglasnost">
	<xsl:template match="/">
		<html>
		  <head>
		    <title>Saglasnost</title>
		    <style>
		      .indent-d {
		        margin-left: 30px;
		      }
		
		      b {
		        font-size: 25px;
				margin-left: 10px;
		      }
		      
		      p {
		      	display: inline;
		      	font-size: 23px;
		      }
		
		      .vl {
				display: inline;
		        border-left: 2px solid black;
		        height: 30px;
				margin: 5px 10px 0px 10px;
		      }
		
		      table.borderless {
		        border-collapse: separate;
		        border-spacing: 10px 0;
		      }
		      
		      h1 {
		      	margin-right: 300px;
		      }
		
		      td {
		        padding: 10px 0;
		      }
		
		      .linija {
		        margin-top: 15px;
		      }
		      
		      .podvuceno {
		      	border-bottom: 2px solid black;
				padding-bottom: 2px;
				font-size: 23px;
		      }
		      
		      #content {
			    position: relative;
			  }
			  
			  #content img {
			    position: absolute;
			    top: 0px;
			    right: 0px;
			  }
			  
			  img.batut-logo {
			  	height: 150px;
			  	width: 300px;
				margin-top: -15px
			  }
			  
			  hr {
			  	border-top: 1px dashed;
			  }

			  input[type="checkbox"] {
				margin-left: 10px;
			  }

			.potpis {
				font-size: 23px;
				margin-right: 100px;
				margin-top: 10px;
				}

			.sig-line {
				border-bottom: 2px solid black;
				padding-right: 650px;
				margin-left: 10px;
			}

			.datum {
				float: right;
				font-size: 23px;
				margin-right: 50px;
				margin-left: 10px;
				margin-top: -40px;
				}
		      
		    </style>
		  </head>
		  <body>
			  <div class="container">
			  	<div id="content">
				    <img class="batut-logo" src="https://files.fm/thumb_show.php?i=wfacsrkxg"/>
				</div>
			    <h1>САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</h1>
			    <h2 style="margin-top: -15px;" >(попуњава пацијент)</h2>
			    <table class="borderless">
			      <tr>
			        <td><b>Држављанство</b></td>
			        <td><b>1) Република Србија</b></td>
			        <td><b class="vl"></b></td>
			        <td><b>ЈМБГ:</b> <span class="podvuceno"><xsl:value-of select="//b:jmbg"/></span></td>
			      </tr>
			      <tr>
			        <td></td>
			        <td><b>2) <span class="podvuceno"><xsl:value-of select="//b:naziv_drzavljanstva"/></span></b></td>
			        <td><b class="vl"></b></td>
			        <td><span class="podvuceno"><xsl:value-of select="//b:broj_pasosa"/></span></td>
			      </tr>
			    </table>
				<div class="justified">
				    <div class="linija">
				      <b>Презиме:</b>
				      <span class="podvuceno"><xsl:value-of select="//za:prezime"/></span>
				      <span class="vl"></span>
				      <b>Име:</b>
				      <span class="podvuceno"><xsl:value-of select="//za:ime"/></span>
				      <span class="vl"></span>
				      <b>Име родитеља:</b>
				      <span class="podvuceno"><xsl:value-of select="//b:ime_roditelja"/></span>
				    </div>
				    <div class="linija">
				      <b>Пол:</b>
				      <xsl:variable name="x" select="//b:pol"/>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'MUSKI'">
		                      <xsl:attribute name="checked">checked</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>М</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'ZENSKI'">
		                      <xsl:attribute name="checked">checked</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>Ж</p>
				      </input>
				      <span class="vl"></span>
				      <b>Датум рођења: </b>
				      <span class="podvuceno">
						  <xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of
							  select="substring(//b:datum, 6, 2)"/>.<xsl:value-of
							  select="substring(//b:datum, 0, 5)"/>
					  </span>
				      <span class="vl"></span>
				      <b>Место рођења: </b>
				      <span class="podvuceno"><xsl:value-of select="//b:mesto_rodjenja"/></span>
				    </div>
				    <div class="linija">
				      <b>Адреса(улица и број):</b>
				      <span class="podvuceno"><xsl:value-of select="//b:ulica"/>, <xsl:value-of select="//b:broj_zgrade"/></span>
				      <span class="vl"></span>
				      <b>Место/Насеље:</b>
				      <span class="podvuceno"><xsl:value-of select="//b:mesto"/></span>
				    </div>
				    <div class="linija">
				      <b>Општина/Град:</b>
				      <span class="podvuceno"><xsl:value-of select="//b:opstina"/></span>
				      <span class="vl"></span>
				      <b>Тел. фиксни:</b>
				      <span class="podvuceno"><xsl:value-of select="//za:broj_fiksnog"/></span>
				    </div>
				    <div class="linija">
				      <b>Тел. мобилни:</b>
				      <span class="podvuceno"><xsl:value-of select="//za:broj_mobilnog"/></span>
				      <span class="vl"></span>
				      <b>Имејл:</b>
				      <span class="podvuceno"><xsl:value-of select="//za:email"/></span>
				    </div>
				    <div class="linija">
				      <b>Радни статус:</b>
				      <xsl:variable name="x" select="//b:radni_status"/>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'ZAPOSLEN'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>запослен</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'NEZAPOSLEN'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>незапослен</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'PENZIONER'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>пензионер</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'UCENIK'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>ученик</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'STUDENT'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>студент</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'DETE'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>дете</p>
				      </input>
				    </div>
				    <div class="linija">
				      <b>Занимање запосленог:</b>
				      <xsl:variable name="x" select="//b:zanimanje_zaposlenog"/>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'ZDRAVSTVENA_ZASTITA'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>здравствена заштита</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'SOCIJALNA_ZASTITA'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>социјална заштита</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'PROSVETA'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>просвета</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'MUP'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>МУП</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'VOJSKA'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>Војска РС</p>
				      </input>
				      <input type="checkbox" disabled="true">
				      	<xsl:choose>
					      	<xsl:when test="$x = 'DRUGO'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                </xsl:choose>
				      	<p>друго</p>
				      </input>
				    </div>
				    <div class="linija">
				      <b>Корисник установе социјалне заштите:</b>
				      <xsl:variable name="x" select="//b:korisnik_ustanove_socijalne_zastite"/>
				      <input type="checkbox" disabled="true">
					      <xsl:choose>
					      	<xsl:when test="$x = 'true'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                  </xsl:choose>
				      </input>
				      ДА
				      <input type="checkbox" disabled="true">
				      	  <xsl:choose>
					      	<xsl:when test="$x = 'false'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                  </xsl:choose>
				      </input>
					  НЕ
				      <span class="vl"></span>
				      <b>Назив и општина седишта: </b>
				      <span class="podvuceno"><xsl:value-of select="//b:naziv_sedista"/><xsl:value-of select="//b:opstina_sedista"/></span>
				    </div>
				    <div class="linija">
				      <b>Изјављујем да:</b>
				      <xsl:variable name="x" select="//b:izjava"/>
				      <input type="checkbox" disabled="true">
					      <xsl:choose>
					      	<xsl:when test="$x = 'true'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                  </xsl:choose>
				      </input>
				      САГЛАСАН САМ
				      <input type="checkbox" disabled="true">
				      	  <xsl:choose>
					      	<xsl:when test="$x = 'false'">
		                      <xsl:attribute name="checked">true</xsl:attribute>
		                    </xsl:when>	
		                  </xsl:choose>
				      </input>
					  НИСАМ САГЛАСАН
				      <p>(означити) са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека):</p>
				      <span class="podvuceno"><xsl:value-of select="//b:naziv_imunoloskog_leka"/></span>
				    </div>
				    <div class="linija"><b>Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним имунолошким леком.</b></div>
					<div style="margin-top:50px;">
						<span class="potpis"> <b>Потпис пацијента или законског заступника пацијента:</b> <br/> <span class="sig-line"></span> </span>
						<span class="datum"
						><b>Датум:<br/> <span class="underlined"><xsl:value-of select="substring(//b:datum, 9, 2)"/>.<xsl:value-of select="substring(//b:datum, 6, 2)"/>.</span> 20<span class="underlined"
							><xsl:value-of select="substring(//b:datum, 3, 2)"/>
							</span>.
						</b>
						</span>
					</div>
				    <div class="linija"><hr/></div>
			    </div>
			    <h2 align="center" style="margin-top: 10px;">ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</h2>
			    <h3 align="center" style="margin-top: -5px;">(попуњава здравствени радник)</h3>
			    <div class="linija">
			      <b>Здравствена установа:</b>
			      <span class="podvuceno"><xsl:value-of select="//b:naziv"/></span>
			      <span class="vl"></span>
			      <b>Вакцинацијски пункт:</b>
			      <span class="podvuceno"><xsl:value-of select="//b:punkt"/></span>
			    </div>
			    <div class="linija">
			      <b>Име, презиме, факсимил и број телефона лекара</b>
			      <span class="podvuceno"><xsl:value-of select="//b:lekar/za:ime"/>, <xsl:value-of select="//b:lekar/za:prezime"/>, <xsl:value-of select="//b:broj_mobilnog"/></span>
			    </div>
			    <div class="linija"><p>Пре давања вакцинe прегледати особу и упознати је са користима и о могућим нежељеним реакцијама после вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке унети у лични картон о извршеним имунизацијама и здравствени картон.</p></div>
			    <table border="1">
			        <tr>
			            <th>Назив вакцине</th>
			            <th>Датум давања вакцине</th>
			            <th>Начин давања вакцине</th>
			            <th>Екстремитет</th>
			            <th>Серија вакцине (лот)</th>
			            <th>Произвођач</th>
			            <th>Нежељена реакцијa</th>
			            <th>Потпис лекарa</th>
			        </tr>
			        <xsl:for-each select="//b:vakcina">
						<tr>
				            <td><xsl:value-of select="za:broj_doze"/></td>
				            <td><xsl:value-of select="za:datum_davanja"/></td>
				            <td>ИМ</td>
				            <td>1)ДР, 2)ЛР</td>
				            <td><xsl:value-of select="za:broj_serije"/></td>
				            <td><xsl:value-of select="za:tip"/></td>
				            <td><xsl:value-of select="b:nuspojava"/></td>
				            <td></td>
				        </tr>
					</xsl:for-each>
			        <tr>
			            <td>Привремене контраиндикације<br/>(начин утврђивања и дијагноза)</td>
			            <td colspan="8">
			            	<xsl:for-each select="//b:kontraindikacija">
								<xsl:value-of select="b:dijagnoza"/>,
							</xsl:for-each>
			            </td>
			        </tr>
					<tr>
						<td>Одлука комисије за трајне контраиндикације (ако постоји, уписати Да):</td>
						<td colspan="8">
							<xsl:variable name="x" select="//b:odluka_komisije_za_trajne_kontraindikacije"/>
							<xsl:choose>
								<xsl:when test="$x = 'true'">
									ДА
								</xsl:when>
								<xsl:when test="$x = 'false'">
									НЕ
								</xsl:when>
							</xsl:choose>
						</td>
					</tr>
			    </table>
			    <div class="linija">
			        <b>Напомена: </b> <p>образац се чува као део медицинске документације пацијента.</p>
			    </div>
		    </div>
		</body>
		</html>

	</xsl:template>
</xsl:stylesheet>