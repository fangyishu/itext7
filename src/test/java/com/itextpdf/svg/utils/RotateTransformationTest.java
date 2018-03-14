package com.itextpdf.svg.utils;

import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.svg.exceptions.SvgLogMessageConstant;
import com.itextpdf.svg.exceptions.SvgProcessingException;
import com.itextpdf.test.annotations.type.UnitTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

@Category(UnitTest.class)
public class RotateTransformationTest {
    @Rule
    public ExpectedException junitExpectedException = ExpectedException.none();

    @Test
    public void normalRotateTest() {
        AffineTransform expected = AffineTransform.getRotateInstance(Math.toRadians(10f), 20f, 30f);
        AffineTransform actual = TransformUtils.parseTransform("rotate(10, 20, 30)");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void noRotateValuesTest() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.TRANSFORM_INCORRECT_NUMBER_OF_VALUES);

        TransformUtils.parseTransform("rotate()");
    }

    @Test
    public void oneRotateValuesTest() {
        AffineTransform expected = AffineTransform.getRotateInstance(Math.toRadians(10d));
        AffineTransform actual = TransformUtils.parseTransform("rotate(10)");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void twoRotateValuesTest() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.TRANSFORM_INCORRECT_NUMBER_OF_VALUES);

        TransformUtils.parseTransform("rotate(23,58)");
    }

    @Test
    public void threeRotateValuesTest() {
        AffineTransform expected = AffineTransform.getRotateInstance(Math.toRadians(23d), 58d, 57d);
        AffineTransform actual = TransformUtils.parseTransform("rotate(23,58, 57)");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void tooManyRotateValuesTest() {
        junitExpectedException.expect(SvgProcessingException.class);
        junitExpectedException.expectMessage(SvgLogMessageConstant.TRANSFORM_INCORRECT_NUMBER_OF_VALUES);

        TransformUtils.parseTransform("rotate(1 2 3 4)");
    }

    @Test
    public void negativeRotateValuesTest() {
        AffineTransform expected = AffineTransform.getRotateInstance(Math.toRadians(-23d), -58d, -1d);
        AffineTransform actual = TransformUtils.parseTransform("rotate(-23,-58,-1)");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ninetyDegreesTest() {
        AffineTransform expected = AffineTransform.getRotateInstance(Math.toRadians(90));
        AffineTransform actual = TransformUtils.parseTransform("rotate(90)");

        Assert.assertEquals(expected, actual);
    }
}