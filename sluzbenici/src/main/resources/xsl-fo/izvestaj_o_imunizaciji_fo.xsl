<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:za="https://www.vakcinacija.rs/zajednicko"
                xmlns:b="https://www.vakcinacija.rs/izvestaj_o_imunizaciji">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvestaj-o-imunizaciji-page">
                    <fo:region-body margin="0.55in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>