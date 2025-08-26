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

package com.carbon.common.exception;

import com.carbon.domain.common.ApiCode;
import com.carbon.domain.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 全局Error/404处理
 * @author Li Jun
 * @since 2021-06-11
 */
@RestController
@Slf4j
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ApiResult<Object> handleError(){
        log.error("404 NOT FOUND");
        return ApiResult.fail(ApiCode.NOT_FOUND);
    }

    // Spring Boot 2.4+ removed ErrorController.getErrorPath(); keep mapping on /error instead
}
