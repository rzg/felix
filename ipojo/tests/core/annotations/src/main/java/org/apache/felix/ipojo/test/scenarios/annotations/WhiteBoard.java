package org.apache.felix.ipojo.test.scenarios.annotations;

import org.apache.felix.ipojo.junit4osgi.OSGiTestCase;
import org.apache.felix.ipojo.junit4osgi.helpers.IPOJOHelper;
import org.apache.felix.ipojo.metadata.Element;

public class WhiteBoard extends OSGiTestCase {

    String typeWI = "org.apache.felix.ipojo.test.scenarios.component.whiteboard.WhiteBoardWIModification";
    String typeWO = "org.apache.felix.ipojo.test.scenarios.component.whiteboard.WhiteBoardWOModification";
    String typeWhiteboards = "org.apache.felix.ipojo.test.scenarios.component.whiteboard.WhiteBoards";
    String namespace = "org.apache.felix.ipojo.whiteboard";

    private IPOJOHelper helper;

    public void setUp() {
        helper = new IPOJOHelper(this);
    }

    public void testMetadataWithOnModification() {
        Element meta = helper.getMetadata(typeWI);
        assertNotNull("Check meta", meta);
        Element[] ext = meta.getElements("wbp", namespace);
        assertEquals("Check size", 1, ext.length);
        String filter = ext[0].getAttribute("filter");
        String onArr = ext[0].getAttribute("onArrival");
        String onDep = ext[0].getAttribute("onDeparture");
        String onMod = ext[0].getAttribute("onModification");


        assertEquals("Check filter", "(foo=true)", filter);
        assertEquals("Check onArrival", "onArrival", onArr);
        assertEquals("Check onDeparture", "onDeparture", onDep);
        assertEquals("Check onModification", "onModification", onMod);

    }

    public void testMetadataWithoutOnModification() {
        Element meta = helper.getMetadata(typeWO);
        assertNotNull("Check meta", meta);
        Element[] ext = meta.getElements("wbp", namespace);
        assertEquals("Check size", 1, ext.length);
        String filter = ext[0].getAttribute("filter");
        String onArr = ext[0].getAttribute("onArrival");
        String onDep = ext[0].getAttribute("onDeparture");
        String onMod = ext[0].getAttribute("onModification");


        assertEquals("Check filter", "(foo=true)", filter);
        assertEquals("Check onArrival", "onArrival", onArr);
        assertEquals("Check onDeparture", "onDeparture", onDep);
        assertNull("Check onModification", onMod);

    }

    public void testWhiteboards() {
        Element meta = helper.getMetadata(typeWhiteboards);
        assertNotNull("Check meta", meta);
        Element[] ext = meta.getElements("whiteboards", namespace);
        assertEquals("Check size", 1, ext.length);

        // Two sub-element
        Element[] wbps = ext[0].getElements("wbp", namespace);
        assertEquals("Check size", 2, wbps.length);

        String filter = wbps[0].getAttribute("filter");
        String onArr = wbps[0].getAttribute("onArrival");
        String onDep = wbps[0].getAttribute("onDeparture");
        String onMod = wbps[0].getAttribute("onModification");

        assertEquals("Check filter", "(foo=true)", filter);
        assertEquals("Check onArrival", "onArrival", onArr);
        assertEquals("Check onDeparture", "onDeparture", onDep);
        assertNull("Check onModification", onMod);

        filter = wbps[1].getAttribute("filter");
        onArr = wbps[1].getAttribute("onArrival");
        onDep = wbps[1].getAttribute("onDeparture");
        onMod = wbps[1].getAttribute("onModification");

        assertEquals("Check filter", "(foo=true)", filter);
        assertEquals("Check onArrival", "onArrival", onArr);
        assertEquals("Check onDeparture", "onDeparture", onDep);
        assertEquals("Check onModification", "onModification", onMod);
    }


}
