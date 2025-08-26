/**
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

package com.carbon.gate.config;

import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 * 
 * 注意：Spring Cloud Gateway 基于 WebFlux，不再使用传统的 Servlet Filter
 * 过滤器功能已迁移到 GlobalFilter 实现
 * 
 * @author Li Jun
 * @since 2018-11-08
 */
@Configuration
public class FilterConfig {
    
    // Spring Cloud Gateway 使用 GlobalFilter 而不是 Servlet Filter
    // 相关过滤器已迁移到：
    // - AuthGlobalFilter: 认证过滤器
    // - CorsGlobalFilter: CORS 跨域过滤器
    
}
