package com.dwnews.storemanagement.dao.bill.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;
import com.dwnews.storemanagement.dao.bill.IBillManagementDAO;
import com.dwnews.storemanagement.dao.item.IItemManagementDAO;
import com.dwnews.storemanagement.entity.Items;
import com.dwnews.storemanagement.entity.ItemsInputOutput;

@Repository("billManagementDAO")
public class BillManagementDAOImpl extends StoreManagementBaseDAOImpl<ItemsInputOutput, Integer> implements IBillManagementDAO {
	
	@Autowired
	private IItemManagementDAO itemManagementDAO;

	@Override
	public Map<String, Object> saveBillVerification(String billIds, String type) {
		Map<String, Object> result=new HashMap<String, Object>();
		if (billIds==null||billIds.equals("")||type==null||type.equals("")){
			result.put("status", 0);
			result.put("data", "parameters error");
		}
		
		if (result.isEmpty()){
			try {
				StringBuffer hql=new StringBuffer("select count(*) from items_input_output iio where iio.bill_id in (");
				hql.append(billIds);
				hql.append(") and iio.bill_verify <> 0");
				List<?> lst=this.getSession().createSQLQuery(hql.toString()).list();
				if (lst!=null&&!lst.isEmpty()){
					Integer count=Integer.parseInt(lst.get(0).toString());
					if (count>0){
						result.put("status", 0);
						result.put("data", "Some rows have verified, please check.");
					}
				}
				if (result.isEmpty()){
					Integer verificationType=Integer.parseInt(type);
					String[] ids=null;
					if (billIds!=null&&!billIds.equals("")&&billIds.contains(",")){
						ids=billIds.split(",");
					}else if (billIds!=null&&!billIds.equals("")&&!billIds.contains(",")){
						ids=new String[1];
						ids[0]=billIds;
					}
					if (verificationType==1){
						ItemsInputOutput bill=null;
						Items item=null;
						Session session=null;
						Transaction tran=null;
						for (String id:ids){
							bill=this.get(Integer.parseInt(id));
							if (bill!=null&&bill.getBillItem()!=null&&bill.getBillItem().getId()!=null){
								item = bill.getBillItem();
								if (item!=null&&bill.getOperationCategory()==1){
									if (bill.getBillCount()>item.getItemStoreCount()){
										result.put("status", 0);
										result.put("data", "Verification was wrong. It is possiblely because the number of output exceeds capacity.");
									}else{
										session=this.sessionFactory.openSession();
										tran=session.beginTransaction();
										bill.setBillVerify(verificationType);
										this.save(bill);
										item.setItemStoreCount(item.getItemStoreCount()-bill.getBillCount());
										itemManagementDAO.save(item);
										tran.commit();
										session.flush();
										session.close();
									}
								}
							}
						}
					}else if (verificationType==2){
						hql=new StringBuffer("update items_input_output iio set iio.bill_verify = 2 where iio.bill_id in (");
						for (int i=0;i<ids.length;i++){
							if (i!=0){
								hql.append(",");
							}
							hql.append(ids[i]);
						}
						hql.append(")");
						this.getSession().createSQLQuery(hql.toString()).executeUpdate();
					}
					if (result.isEmpty()){
						result.put("status", 1);
						result.put("data", "verification operatioin success.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", 0);
				result.put("data", "verification operatioin failed.");
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> deleteBill(String billIds) {
		Map<String, Object> result=new HashMap<String, Object>();
		if (billIds==null||billIds.equals("")){
			result.put("status", 0);
			result.put("data", "parameters error");
		}
		if (result.isEmpty()){
			StringBuffer hql=new StringBuffer("select count(*) from items_input_output iio where iio.bill_id in (");
			hql.append(billIds);
			hql.append(") and iio.bill_verify <> 0");
			List<?> lst=this.getSession().createSQLQuery(hql.toString()).list();
			if (lst!=null&&!lst.isEmpty()){
				Integer count=Integer.parseInt(lst.get(0).toString());
				if (count>0){
					result.put("status", 0);
					result.put("data", "Some rows have verified, please check.");
				}
			}
			
			if (result.isEmpty()){
				String[] ids=null;
				if (billIds!=null&&!billIds.equals("")&&billIds.contains(",")){
					ids=billIds.split(",");
				}else if (billIds!=null&&!billIds.equals("")&&!billIds.contains(",")){
					ids=new String[1];
					ids[0]=billIds;
				}
				Object[] obj=new Object[ids.length];
				hql=new StringBuffer("delete from ItemsInputOutput iio where id in (");
				for (int i=0;i<ids.length;i++){
					if (i==0){
						hql.append("?");
					}else{
						hql.append(",?");
					}
					obj[i]=Integer.parseInt(ids[i]);
				}
				hql.append(")");
				try{
					this.batchExecute(hql.toString(), obj);
					result.put("status", 1);
					result.put("data", "Operation success.");
				}catch(Exception ex){
					ex.printStackTrace();
					result.put("status", 1);
					result.put("data", "Delete Bills failed, try again.");
				}
			}
		}
		return result;
	}
	
}
