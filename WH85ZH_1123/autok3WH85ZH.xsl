<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>mennyi elem</h2>
                <ol>
                    <li>
                        <xsl:value-of select="count(autok/auto)" />
                    </li>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>