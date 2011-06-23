package org.apache.commons.digester3;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static java.lang.String.format;

import org.apache.commons.beanutils.MethodUtils;

/**
 * <p>
 * Rule implementation that calls a method on the (top-1) (parent) object, passing the top object (child) as an
 * argument. It is commonly used to establish parent-child relationships.
 * </p>
 * <p>
 * This rule now supports more flexible method matching by default. It is possible that this may break (some) code
 * written against release 1.1.1 or earlier. See {@link #isExactMatch()} for more details.
 * </p>
 * <p>
 * Note that while CallMethodRule uses commons-beanutils' data-conversion functionality (ConvertUtils class) to convert
 * parameter values into the appropriate type for the parameter to the called method, this rule does not. Needing to use
 * ConvertUtils functionality when building parent-child relationships is expected to be very rare; however if you do
 * need this then instead of using this rule, create a CallMethodRule specifying targetOffset of 1 in the constructor.
 * </p>
 */
public class SetNextRule
    extends Rule
{

    // ----------------------------------------------------------- Constructors

    /**
     * Construct a "set next" rule with the specified method name. The method's argument type is assumed to be the class
     * of the child object.
     * 
     * @param methodName Method name of the parent method to call
     */
    public SetNextRule( String methodName )
    {
        this( methodName, null );
    }

    /**
     * Construct a "set next" rule with the specified method name.
     * 
     * @param methodName Method name of the parent method to call
     * @param paramType Java class of the parent method's argument (if you wish to use a primitive type, specify the
     *            corresonding Java wrapper class instead, such as <code>java.lang.Boolean</code> for a
     *            <code>boolean</code> parameter)
     */
    public SetNextRule( String methodName, String paramType )
    {
        this.methodName = methodName;
        this.paramType = paramType;
    }

    // ----------------------------------------------------- Instance Variables

    /**
     * The method name to call on the parent object.
     */
    protected String methodName = null;

    /**
     * The Java class name of the parameter type expected by the method.
     */
    protected String paramType = null;

    /**
     * Should we use exact matching. Default is no.
     */
    protected boolean useExactMatch = false;

    // --------------------------------------------------------- Public Methods

    /**
     * <p>
     * Is exact matching being used.
     * </p>
     * <p>
     * This rule uses <code>org.apache.commons.beanutils.MethodUtils</code> to introspect the relevent objects so that
     * the right method can be called. Originally, <code>MethodUtils.invokeExactMethod</code> was used. This matches
     * methods very strictly and so may not find a matching method when one exists. This is still the behaviour when
     * exact matching is enabled.
     * </p>
     * <p>
     * When exact matching is disabled, <code>MethodUtils.invokeMethod</code> is used. This method finds more methods
     * but is less precise when there are several methods with correct signatures. So, if you want to choose an exact
     * signature you might need to enable this property.
     * </p>
     * <p>
     * The default setting is to disable exact matches.
     * </p>
     * 
     * @return true iff exact matching is enabled
     * @since Digester Release 1.1.1
     */
    public boolean isExactMatch()
    {
        return useExactMatch;
    }

    /**
     * <p>
     * Set whether exact matching is enabled.
     * </p>
     * <p>
     * See {@link #isExactMatch()}.
     * </p>
     * 
     * @param useExactMatch should this rule use exact method matching
     * @since Digester Release 1.1.1
     */
    public void setExactMatch( boolean useExactMatch )
    {
        this.useExactMatch = useExactMatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void end( String namespace, String name )
        throws Exception
    {
        // Identify the objects to be used
        Object child = getDigester().peek( 0 );
        Object parent = getDigester().peek( 1 );
        if ( getDigester().getLogger().isDebugEnabled() )
        {
            if ( parent == null )
            {
                getDigester().getLogger().debug( format( "[SetNextRule]{%s} Call [NULL PARENT].%s(%s)",
                                                         getDigester().getMatch(),
                                                         methodName,
                                                         child ) );
            }
            else
            {
                getDigester().getLogger().debug( format( "[SetNextRule]{%s} Call %s.%s(%s)",
                                                         getDigester().getMatch(),
                                                         parent.getClass().getName(),
                                                         methodName,
                                                         child ) );
            }
        }

        // Call the specified method
        Class<?> paramTypes[] = new Class<?>[1];
        if ( paramType != null )
        {
            paramTypes[0] = getDigester().getClassLoader().loadClass( paramType );
        }
        else
        {
            paramTypes[0] = child.getClass();
        }

        if ( useExactMatch )
        {
            MethodUtils.invokeExactMethod( parent, methodName, new Object[] { child }, paramTypes );
        }
        else
        {
            MethodUtils.invokeMethod( parent, methodName, new Object[] { child }, paramTypes );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return format( "SetNextRule[methodName=%s, paramType=%s]", methodName, paramType );
    }

}
