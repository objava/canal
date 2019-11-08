/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.otter.canal.parse.inbound.extension;

/**
 * @author jianghang 2012-11-7 下午02:11:46
 * @version 4.1.2
 */
public class DefaultExtensionFactory implements ExtensionFactory {

    private ExtensionMemoryMirror<ExtensionData, Object> resolverCache;

    @Override
    public <T> T getExtension(Class<T> type, ExtensionData extensionData) {
        return null;
    }
//    private JdkCompiler                                  jdkCompiler;

//    public DefaultExtensionFactory(){
//        resolverCache = new ExtensionMemoryMirror<>(extensionData -> getExtensionInternal(extensionData));
//    }

//    public <T> T getExtension(Class<T> type, ExtensionData extensionData) {
//        return (T) resolverCache.get(extensionData);
//    }

//    private Object getExtensionInternal(ExtensionData extensionData) {
//        Class<?> clazz = null;
//        String fullname = StringUtils.EMPTY;
//        if (extensionData.getExtensionDataType().isClazz() && StringUtils.isNotBlank(extensionData.getClazzPath())) {
//            clazz = scan(extensionData.getClazzPath());
//            fullname = "[" + extensionData.getClazzPath() + "]ClassPath";
////        } else if (extensionData.getExtensionDataType().isSource()
////                   && StringUtils.isNotBlank(extensionData.getSourceText())) {
////            JavaSource javaSource = new JavaSource(extensionData.getSourceText());
////            clazz = jdkCompiler.compile(javaSource);
////            fullname = "[" + javaSource.toString() + "]SourceText";
//        }
//
//        if (clazz == null) {
//            throw new RuntimeException("ERROR ## classload this fileresolver=" + fullname + " has an error");
//        }
//
//        try {
//            return clazz.newInstance();
//        } catch (Exception e) {
//            throw new RuntimeException("ERROR ## classload this fileresolver=" + fullname + " has an error", e);
//        }
//    }


}
