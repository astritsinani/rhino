package org.mozilla.javascript;

import junit.framework.TestCase;

public class NativeDateTest extends TestCase {

    public void testDateConstructorAcceptsIsoFormattedStrings() {
        assertEvaluates("2014-06-18T13:03:18.910Z", "var date = new Date('2014-06-18T15:03:18.910+02:00'); date.toISOString();");
        assertEvaluates("2014-06-18T13:03:18.910Z", "var date = new Date('2014-06-18T13:03:18.910Z'); date.toISOString();");
        assertEvaluates("2014-06-20T11:55:03.182Z", "var date = new Date('2014-06-20T13:55:03.182+02:00'); date.toISOString();");
        assertEvaluates("2014-06-20T11:55:03.182Z", "var date = new Date('2014-06-20T11:55:03.182Z'); date.toISOString();");
    }

    public void testParsingDatesAcceptsIsoFormattedStrings() {
        assertEvaluates(0.0, "Date.parse('1970-01-01T00:00:00+00:00')");
        assertEvaluates(14400000.0, "Date.parse('1970-01-01T00:00:00-04:00')");
        assertEvaluates(807926400000.0, "Date.parse('1995-08-09T00:00:00+00:00')");
    }

    private void assertEvaluates(final Object expected, final String source) {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Object result = cx.evaluateString(scope, source, "test.js", 1, null);
            assertEquals(expected, result);
        } finally {
            Context.exit();
        }
    }
}
