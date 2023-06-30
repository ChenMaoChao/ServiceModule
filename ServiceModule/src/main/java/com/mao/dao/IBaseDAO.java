package com.mao.dao;

import com.mao.vo.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 进行DAO接口的公共定义
 * @param <K> 要设置的主键类型
 * @param <V> 要操作的vo类型
 */
public interface IBaseDAO<K,V> {
    /**
     * 实现数据的增加操作，该方法主要执行的是insert更新语句
     * @param vo 保存要增加数据的信息类，该类确定已经合法
     * @return 增加成功返回true，否则返回false
     * @throws SQLException 数据库执行异常
     */
    public boolean doCreate(V vo) throws SQLException;

    /**
     * 实现数据的修改操作，将根据ID进行数据的整体更新
     * @param vo 保存有要更新的完整数据
     * @return 更新成功返回true，否则返回false
     * @throws SQLException 数据库执行异常
     */
    public boolean doEdit(V vo) throws SQLException;

    /**
     * 执行数据的删除操作，可以删除给定范围中的数据信息
     * @param ids 要删除的ID编号，该编号不允许重复
     * @return 删除成功返回true，否则返回false
     * @throws SQLException 数据库执行异常
     */
    public boolean doRemove(Set<K> ids) throws SQLException;

    /**
     * 根据数据表中的id进行指定数据信息的获取
     * @param id 要查询的数据ID
     * @return 如果数据存在，则数据以vo的形式返回，如果数据不存在返回null
     * @throws SQLException 数据库执行异常
     */
    public V findById(String id) throws SQLException;

    /**
     * 根据数据表中注册的email地址查询对应的用户信息
     * @param email 要查询的email地址
     * @return 如果数据存在，则数据以vo的形式返回，如果数据不存在返回null
     * @throws SQLException 数据库执行异常
     */
    public V findByEmail(String email) throws SQLException;

    /**
     * 查询数据表中的全部数据记录，所有记录将以集合的形式返回
     * @return 如果表中存在有记录，则数据以list的集合返回，里面会有多个vo类的对象实例，如果没有数据则集合长度为0(size() == 0)
     * @throws SQLException 数据库执行异常
     */
    public List<V> findAll() throws SQLException;

    /**
     * 执行数据的全部分页信息的查询
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的数据行数
     * @return 如果表中存在有记录，则数据以list的集合返回，里面会有多个vo类的对象实例，如果没有数据则集合长度为0(size() == 0)
     * @throws SQLException 数据库执行异常
     */
    public List<V> findSplit(Integer currentPage,Integer lineSize) throws SQLException;

    /**
     * 执行数据的全部分页信息的查询
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的行数
     * @param column 模糊查询
     * @param keyWord 模糊查询关键字
     * @return 如果表中存在有记录，则数据以list的集合返回，里面会有多个vo类的对象实例，如果没有数据则集合长度为0(size() == 0)
     * @throws SQLException 数据库执行异常
     */
    public List<V> findSplit(Integer currentPage,Integer lineSize,String column,String keyWord) throws SQLException;

    /**
     * 进行数据表的数据量统计
     * @return SQL语句中count()函数的返回结果
     * @throws SQLException 数据执行异常
     */
    public Long getAllCount() throws SQLException;

    /**
     * 进行数据表中的数据量的模糊统计
     * @param column 模糊查询列
     * @param keyWord 模糊查询关键字
     * @return SQL语句中count()函数的返回结果
     * @throws SQLException 数据执行异常
     */
    public Long getAllCount(String column,String keyWord) throws SQLException;
}
