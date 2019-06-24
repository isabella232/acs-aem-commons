/*
 * #%L
 * ACS AEM Commons Bundle
 * %%
 * Copyright (C) 2017 Adobe
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.adobe.acs.commons.httpcache.store.jcr.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public final class JCRHttpCacheStoreImplTest {

    private final JCRHttpCacheStoreMocks mocks = new JCRHttpCacheStoreMocks();
    private final JCRHttpCacheStoreImpl store = mocks.getStore();
    
    public JCRHttpCacheStoreImplTest() throws Exception {
    }

    @Test
    public void testPutIntoCache() throws Exception {
        store.put(mocks.getCacheKey(), mocks.getCacheContent());
        verify(JCRHttpCacheStoreMocks.getLog(), never()).error(anyString(), any(Exception.class));
        verify(mocks.getResourceResolver(), times(1)).close();
        verify(mocks.getSession(), times(1)).save();
        verify(mocks.getEntryNodeWriter(), times(1)).write();
    }

    @Test
    public void testContains() throws Exception {
        store.contains(mocks.getCacheKey());
    }

    @Test
    public void testInvalidate() throws Exception {
        store.invalidate(mocks.getCacheKey());
    }

}
