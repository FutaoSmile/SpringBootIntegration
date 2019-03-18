package ${serviceImplPackagePath};

import ${daoPackagePath}.${className}Dao;
import ${serviceFacePackagePath}.${className}Service;
import ${entityPackagePath}.${className};
import com.futao.springbootdemo.model.system.SystemConfig;
import com.futao.springbootdemo.utils.ServiceTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import java.util.List;
import com.futao.springbootdemo.foundation.configuration.HibernateValidatorConfiguration;


/**
* ${classDesc}
*
* @author ${authorName}
* Created on ${createDate}.
*/
@Transactional(isolation = Isolation.DEFAULT, timeout = SystemConfig.SERVICE_TRANSACTION_TIMEOUT_SECOND, rollbackFor = Exception.class)
@Service
public class ${className}ServiceImpl implements ${className}Service{

@Autowired
private ${className}Dao ${className?lower_case}Dao;

/**
* 新增${classDesc}
*
* @return ${classDesc}
*/
@Override
public ${className} add(){
//参数封装成对象
${className} ${className?lower_case} = new ${className}();
//TODO("赋值")

//参数合法性校验
HibernateValidatorConfiguration.validate(${className?lower_case});
//调用dao层
${className?lower_case}Dao.add(${className?lower_case});
return ${className?lower_case};
}

/**
* 删除${classDesc}
*
* @param id 要删除的${classDesc}主键
* @return ${classDesc}
*/
@Override
public ${className} delete(String id){
//调用dao层
${className?lower_case}Dao.delete(${className?lower_case}Dao.byId(id).getId());
return ${className?lower_case};
}

/**
* 修改${classDesc}
*
* @param id 要修改的${classDesc}主键
* @return ${classDesc}
*/
@Override
public ${className} update(String id){
//先查询数据是否存在
${className} ${className?lower_case} = ServiceTools.checkResultNullAndThrow(${className?lower_case}Dao.byId(id));
//TODO("赋用户修改之后的值")

//参数合法性校验
HibernateValidatorConfiguration.validate(${className?lower_case});
//调用dao层
${className?lower_case}Dao.update(${className?lower_case});
return ${className?lower_case};
}

/**
* 查询${classDesc}列表
*
* @return ${classDesc}列表
*/
@Override
public List<${className}> list(){
//调用dao层
return ${className?lower_case}Dao.list();
}


/**
* 获取${classDesc}详情
*
* @param id 要查询的${classDesc}主键
* @return ${classDesc}
*/
@Override
public ${className} byId(String id){
//调用dao层
return ${className?lower_case}Dao.byId(id);
}


}
