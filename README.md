# Ksoup
优雅地把HTML解析为Java/Kotlin实体对象

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0)
[![MavenCentral](https://img.shields.io/badge/%20MavenCentral%20-1.1.1-5bc0de.svg?style=flat-square)](https://repo1.maven.org/maven2/com/zwenkai/delegationadapter)

## 引入

```
implementation 'com.zwenkai:ksoup:1.1.1'
```

## 说明

1. 跟进Html结构定义Model

    ```kotlin
    @Pick("body")
   class HomeEntity {
   
       @Pick("div.wap_searchbox form#wap_a_searchform div input#wap_a_search", attr = "placeholder")
       var searchPlaceholder: String = ""
   
       @Pick("div.box div.channel div.channellist")
       var channelList: List<ChannelItem>? = null
   }
   
   class ChannelItem {
   @Pick("a div.chamsg")
   var title: String = ""
   
       @Pick("a img", attr = Attrs.SRC)
       var url: String = ""
   }
   ```

2. Html解析

   ```kotlin
   val model = ksoup.parse(htmlContent, XxxModel::class.java)
   ```

## License

```text
Copyright 2021 Kevin zhou

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
