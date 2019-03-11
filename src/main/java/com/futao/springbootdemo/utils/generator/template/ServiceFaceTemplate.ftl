package ${serviceFacePackagePath};

import ${entityPackagePath}.${className};

import java.util.List;

/**
* ${classDesc}接口定义
*
* @author ${authorName}
* Created on ${createDate}.
*/
public interface ${className}Service{

/**
* 新增${classDesc}
*
* @return ${classDesc}
*/
${className} add();

/**
* 删除${classDesc}
*
* @param id 要删除的${classDesc}主键
* @return ${classDesc}
*/
${className} delete(String id);


/**
* 修改${classDesc}
*
* @param id 要修改的${classDesc}主键
* @return ${classDesc}
*/
${className} update(String id);

/**
* 查询${classDesc}列表
*
* @return ${classDesc}列表
*/
List<${className}> list();

/**
* 获取${classDesc}详情
*
* @param id 要查询的${classDesc}主键
* @return ${classDesc}
*/
${className} byId(String id);
}
