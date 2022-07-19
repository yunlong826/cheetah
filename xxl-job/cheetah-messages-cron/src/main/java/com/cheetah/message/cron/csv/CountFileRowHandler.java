package com.cheetah.message.cron.csv;

import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvRowHandler;
import lombok.Data;

/**
 * Description: 统计当前文件有多少行
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:30
 */
@Data
public class CountFileRowHandler implements CsvRowHandler {
    private long rowSize;

    @Override
    public void handle(CsvRow row) {
        rowSize++;
    }

    public long getRowSize() {
        return rowSize;
    }
}
