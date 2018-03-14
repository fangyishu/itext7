package com.itextpdf.svg.utils;

import com.itextpdf.svg.exceptions.SvgLogMessageConstant;
import com.itextpdf.svg.exceptions.SvgProcessingException;
import com.itextpdf.test.annotations.type.UnitTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

@Category(UnitTest.class)
public class SvgCssUtilsTest {

    @Rule
    public ExpectedException junitExpectedException = ExpectedException.none();

    @Test
    public void normalParseFloat() {
        Float expected = 3.0f;
        Float actual = SvgCssUtils.parseFloat("3.0");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void negativeFloatParsing() {
        Float expected = -3.0f;
        Float actual = SvgCssUtils.parseFloat("-3.0");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void textFloatParsing() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.FLOAT_PARSING_NAN);

        SvgCssUtils.parseFloat("Definitely not a float.");
    }

    @Test
    public void doubleFloatParsing() {
        Float expected = 2.0f;
        Float actual = SvgCssUtils.parseFloat("2.0d");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mixedTextFloatParsing() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.FLOAT_PARSING_NAN);

        SvgCssUtils.parseFloat("15.0WaitWhat?30.0f");
    }

    @Test
    public void nullFloatParsing() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.FLOAT_PARSING_NAN);

        SvgCssUtils.parseFloat(null);
    }

    @Test
    public void commaSplitValueTest() {
        String input = "a,b,c,d";
        List<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");

        List<String> actual = SvgCssUtils.splitValueList(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whitespaceSplitValueTest() {
        String input = "1 2 3 4";
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");

        List<String> actual = SvgCssUtils.splitValueList(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void newLineSplitValueTest() {
        String input = "1\n2\n3\n4";
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");

        List<String> actual = SvgCssUtils.splitValueList(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tabSplitValueTest() {
        String input = "1\t2\t3\t4";
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");

        List<String> actual = SvgCssUtils.splitValueList(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mixedCommaWhitespaceSplitValueTest() {
        String input = "1,2 a,b";
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("a");
        expected.add("b");

        List<String> actual = SvgCssUtils.splitValueList(input);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void nullSplitValueTest() {
        List<String> actual = SvgCssUtils.splitValueList(null);

        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void emptySplitValueTest() {
        List<String> actual = SvgCssUtils.splitValueList("");

        Assert.assertTrue(actual.isEmpty());
    }
}