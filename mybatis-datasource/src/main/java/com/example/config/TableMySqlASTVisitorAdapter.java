package com.example.config;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLExistsExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlTableIndex;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.example.entiy.UserDto;
import com.example.util.UserLocalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableMySqlASTVisitorAdapter extends MySqlASTVisitorAdapter {
    public static final Map<String,String> tableMap=new HashMap<>();
    static {
        tableMap.put("1","_one");
    }
    @Override
    public boolean visit(SQLExprTableSource sqlExistsExpr){
        UserDto user = UserLocalUtil.getUser();
        String suffix = tableMap.get(user.getUserId());
        String tableName = sqlExistsExpr.getTableName();
        changeName((MySqlSelectQueryBlock) sqlExistsExpr.getParent());
        sqlExistsExpr.setExpr(sqlExistsExpr.getExpr()+suffix);
        return true;
    }
    private void changeName(MySqlSelectQueryBlock mySqlSelectQueryBlock){
        List<SQLSelectItem> selectList = mySqlSelectQueryBlock.getSelectList();
        mySqlSelectQueryBlock.addCondition("s.PHONE=13724889158");
    }
    @Override
    public boolean visit(MySqlTableIndex x){
        System.out.printf(  ""+x.getName());
        return true;
    }
}
