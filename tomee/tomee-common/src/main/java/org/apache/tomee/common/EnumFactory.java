/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tomee.common;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * @version $Rev$ $Date$
 */
public class EnumFactory implements ObjectFactory {

    public static final String ENUM_VALUE = "enumValue";

    public Object getObjectInstance(Object object, Name name, Context context, Hashtable environment) throws Exception {
        if (!(object instanceof Reference)) return null;

        final Reference reference = ((Reference) object);

        final String typeName = reference.getClassName();

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        final Class<?> type = loader.loadClass(typeName);

        final String value = NamingUtil.getProperty(reference, ENUM_VALUE);

        return Enum.valueOf((Class<Enum>) type, value);
    }

}
