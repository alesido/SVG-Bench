package org.alsiessko.android.svgbench;

import android.test.AndroidTestCase;

import org.alsiessko.android.svg.SvgSaxParser;

/**
 * Created by Alexander S. Sidorov on 6/19/15.
 */
public class BeamFootprintSvgTest extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConversion() throws Exception
    {
        AzercosmosFootprintConverter convertor = new AzercosmosFootprintConverter();

        SvgSaxParser parser = new SvgSaxParser(getContext().getAssets(), "sv/azercosmos_01.svg", convertor);
        parser.parse();

        String kmlFileContent = convertor.getKmlString();
    }
}
