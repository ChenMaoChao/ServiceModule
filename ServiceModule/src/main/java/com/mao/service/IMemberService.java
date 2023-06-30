package com.mao.service;

import com.mao.vo.Member;

import java.util.List;
import java.util.Map;

public interface IMemberService {

    /**
     * 实现数据的增加处理操作，在进行数据增加的时候需要采用如下的验证操作：
     * 1、检测输入的数据是否正确，如：年龄的范围或者性别范围
     * 2、调用IMemberDAO.findById()方法判断要增加的ID是否存在
     * 3、调用IMemberDAO.findByEmail()方法判断要增加的Email是否重复
     * 4、如果一切都没有问题则表示该数据可以保存，调用IMemberDAO.doCreate()
     * @param vo 保存要增加的数据
     * @return 信息增加成功返回true，否则返回false
     */
    public boolean add(Member vo) throws Exception;

    /**
     * 进行数据的更新操作，该操作调用的是IMemberDAO.doEdit()方法进行全部数据的更新，根据id完成
     * @param vo 要更新的数据，一定要包含有用户id
     * @return 更新成功返回true，否则返回false
     */
    public boolean edit(Member vo) throws Exception;

    /**
     * 实现指定数据的ID删除,重复的数据不删除，调用的IMemberDAO.doRemove()方法
     * @param ids 要删除的id集合
     * @return 删除成功返回true,否则返回false
     */
    public boolean removeById(String... ids) throws Exception;

    /**
     * 根据id编号查询一个用户的详细信息，调用IMemberDAO.findById()
     * @param id 要查询用户id
     * @return 如果数据存在则直接以vo的形式返回，如果不存在返回null
     */
    public Member get(String id) throws Exception;

    /**
     * 查询用户表中的全部数据，调用的IMemberDAO.findAll()
     * @return 所有数据以List集合的形式返回，如果没有数据集合长度为0(size() == 0)
     */
    public List<Member> list() throws Exception;

    /**
     * 进行数据的分页显示，如果设置了查询字段，则进行数据的模糊查询操作，该操作分为两类：
     * 1、如果现在设置了column与keyword内容均为空，则进行全体数据的分页显示以及统计
     * 1、如果现在设置了column与keyword内容均不为空，则进行全体数据的分页显示以及统计
     * @param currentPage 当所在页
     * @param lineSize 每页显示的行数
     * @param column 模糊查询列
     * @param keyWord 模糊查询关键字
     * @return 返回有两类结果：
     * 1、key = allMembers、value = List集合，数据的查询结果
     * 2、key = allRecorders、value = Long统计结果
     */
    public Map<String, Object> split(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;
}
