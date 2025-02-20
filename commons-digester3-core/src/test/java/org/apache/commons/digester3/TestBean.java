/* $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.digester3;

import org.apache.commons.digester3.annotations.rules.CallParam;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * General purpose test bean for Digester tests.
 */
public class TestBean
{

    public TestBean()
    {
        // do nothing
    }

    @ObjectCreate( pattern = "toplevel/bean" )
    public TestBean( @CallParam( pattern = "toplevel/bean", attributeName = "boolean" ) final boolean booleanProperty,
                     @CallParam( pattern = "toplevel/bean", attributeName = "double" ) final double doubleProperty )
    {
        setBooleanProperty( booleanProperty );
        setDoubleProperty( doubleProperty );
    }

    /**
     * see DIGESTER-154
     *
     * @param booleanProperty
     * @param doubleProperty
     */
    public TestBean( final Boolean booleanProperty, final Double doubleProperty )
    {
        this( booleanProperty.booleanValue(), doubleProperty.doubleValue() );
    }

    // ------------------------------------------------------------- Properties

    /**
     * A boolean property whose initial value is true.
     */
    private boolean booleanProperty = true;

    public boolean getBooleanProperty()
    {
        return ( booleanProperty );
    }

    public void setBooleanProperty( final boolean booleanProperty )
    {
        this.booleanProperty = booleanProperty;
    }

    /**
     * A double property.
     */
    private double doubleProperty = 321.0;

    public double getDoubleProperty()
    {
        return ( this.doubleProperty );
    }

    public void setDoubleProperty( final double doubleProperty )
    {
        this.doubleProperty = doubleProperty;
    }

    /**
     * A boolean property whose initial value is false
     */
    private boolean falseProperty = false;

    public boolean getFalseProperty()
    {
        return ( falseProperty );
    }

    public void setFalseProperty( final boolean falseProperty )
    {
        this.falseProperty = falseProperty;
    }

    /**
     * A float property.
     */
    private float floatProperty = (float) 123.0;

    public float getFloatProperty()
    {
        return ( this.floatProperty );
    }

    public void setFloatProperty( final float floatProperty )
    {
        this.floatProperty = floatProperty;
    }

    /**
     * Integer arrays that are accessed as an array as well as indexed.
     */
    private int intArray[] = { 0, 10, 20, 30, 40 };

    public int[] getIntArray()
    {
        return ( this.intArray );
    }

    public void setIntArray( final int intArray[] )
    {
        this.intArray = intArray;
    }

    private final int intIndexed[] = { 0, 10, 20, 30, 40 };

    public int getIntIndexed( final int index )
    {
        return ( intIndexed[index] );
    }

    public void setIntIndexed( final int index, final int value )
    {
        intIndexed[index] = value;
    }

    private int intMultibox[] = new int[0];

    public int[] getIntMultibox()
    {
        return ( this.intMultibox );
    }

    public void setIntMultibox( final int intMultibox[] )
    {
        this.intMultibox = intMultibox;
    }

    /**
     * An integer property.
     */
    private int intProperty = 123;

    public int getIntProperty()
    {
        return ( this.intProperty );
    }

    public void setIntProperty( final int intProperty )
    {
        this.intProperty = intProperty;
    }

    /**
     * A long property.
     */
    private long longProperty = 321;

    public long getLongProperty()
    {
        return ( this.longProperty );
    }

    public void setLongProperty( final long longProperty )
    {
        this.longProperty = longProperty;
    }

    /**
     * A multiple-String SELECT element.
     */
    private String[] multipleSelect = { "Multiple 3", "Multiple 5", "Multiple 7" };

    public String[] getMultipleSelect()
    {
        return ( this.multipleSelect );
    }

    public void setMultipleSelect( final String multipleSelect[] )
    {
        this.multipleSelect = multipleSelect;
    }

    /**
     * A nested reference to another test bean (populated as needed).
     */
    private TestBean nested = null;

    public TestBean getNested()
    {
        if ( nested == null ) {
            nested = new TestBean();
        }
        return ( nested );
    }

    /**
     * A String property with an initial value of null.
     */
    private String nullProperty = null;

    public String getNullProperty()
    {
        return ( this.nullProperty );
    }

    public void setNullProperty( final String nullProperty )
    {
        this.nullProperty = nullProperty;
    }

    /**
     * A short property.
     */
    private short shortProperty = (short) 987;

    public short getShortProperty()
    {
        return ( this.shortProperty );
    }

    public void setShortProperty( final short shortProperty )
    {
        this.shortProperty = shortProperty;
    }

    /**
     * A single-String value for a SELECT element.
     */
    private String singleSelect = "Single 5";

    public String getSingleSelect()
    {
        return ( this.singleSelect );
    }

    public void setSingleSelect( final String singleSelect )
    {
        this.singleSelect = singleSelect;
    }

    /**
     * String arrays that are accessed as an array as well as indexed.
     */
    private String stringArray[] = { "String 0", "String 1", "String 2", "String 3", "String 4" };

    public String[] getStringArray()
    {
        return ( this.stringArray );
    }

    public void setStringArray( final String stringArray[] )
    {
        this.stringArray = stringArray;
    }

    private final String stringIndexed[] = { "String 0", "String 1", "String 2", "String 3", "String 4" };

    public String getStringIndexed( final int index )
    {
        return ( stringIndexed[index] );
    }

    public void setStringIndexed( final int index, final String value )
    {
        stringIndexed[index] = value;
    }

    /**
     * A String property.
     */
    private String stringProperty = "This is a string";

    public String getStringProperty()
    {
        return ( this.stringProperty );
    }

    public void setStringProperty( final String stringProperty )
    {
        this.stringProperty = stringProperty;
    }

    /**
     * An empty String property.
     */
    private String emptyStringProperty = "";

    public String getEmptyStringProperty()
    {
        return ( this.emptyStringProperty );
    }

    public void setEmptyStringProperty( final String emptyStringProperty )
    {
        this.emptyStringProperty = emptyStringProperty;
    }

}
