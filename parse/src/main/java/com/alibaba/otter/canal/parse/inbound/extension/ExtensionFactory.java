package com.alibaba.otter.canal.parse.inbound.extension;

public interface ExtensionFactory {

  /**
   * Get extension.
   *
   * @param type object type.
   * @param name object name.
   * @return object instance.
   */
  <T> T getExtension(Class<T> type, ExtensionData extensionData);

}
