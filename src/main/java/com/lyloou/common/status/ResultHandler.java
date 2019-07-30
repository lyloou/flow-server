package com.lyloou.common.status;


public interface ResultHandler {

    Result msgResult(StatusCode code);

    Result dataResult(StatusCode code, Object data);

}
