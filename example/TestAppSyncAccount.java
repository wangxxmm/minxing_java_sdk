import com.minxing.client.app.AppAccount;
import com.minxing.client.json.JSONException;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.model.Error;
import com.minxing.client.model.MxException;
import com.minxing.client.organization.Department;
import com.minxing.client.organization.Network;
import com.minxing.client.organization.User;

public class TestAppSyncAccount {

	static boolean all = false;// 是否全网管理 ，如果是需要设置network_name
	static String networkName = "t.cn";
	static String deptPrefix = "09800";

	public static void main(String[] args) throws Exception {

		AppAccount account = AppAccount.loginByAccessToken("http://localhost:3000",
				"cGTsJXPAJeEwUEXvt2SvJ--0q7cPunUqvc4AMKb8i6y-PUti");

		testSetRootDepartment(account);
		testAddDepartment(account);
		testUpdateDepartment(account);
		testDeleteDepartment(account);

		testAddUser(account);
		testUpdateUser(account);

		testGenerateToken(account);

		testAdd10Dept(account);
		testAdd10Dept(account);
		testUpdate5toSameDept(account);
		testUpdate10Dept(account);
		testAdd10User(account);
		testUpdate5toSameUser(account);
		testUpdate10User(account);
		testDelete10User(account);
		testDelete10Dept(account);

		testAddNetworks(account);
		testUpdateNetworks(account);
		testRemoveNetworks(account);
	}

	private static void testSetRootDepartment(AppAccount account) {
		Department dept = new Department();
		dept.setRoot(true);// 设置是否根机构,true-是根机构
		dept.setDept_code("iii004");// 必填
		dept.setDisplay_order("10");
		dept.setShortName("部门名称2");// 必填
		//dept.setNetwork_id("usa");

		// ret_status.getErrorCode()=0，表明是成功，其他信息表明失败了，失败原因为ret_status.getErrorMessage()。其他接口同理。

		try {
			Department new_dept = account.createDepartment(dept);
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testUpdate5toSameDept(AppAccount account) {

		for (int i = 0; i < 5; i++) {
			Department dept = new Department();
			dept.setDept_code(deptPrefix + (i + 5));
			dept.setDisplay_order(String.valueOf(10 - i));
			// dept.setParent_dept_code("11");
			if (all)
				dept.setNetwork_name(networkName);
			dept.setShortName("子公司" + (i + 5));
			// ret_status.getErrorCode()=0，表明是成功，其他信息表明失败了，失败原因为ret_status.getErrorMessage()

			try {
				account.updateDepartment(dept);
			} catch (ApiErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void testUpdate5toSameUser(AppAccount account) {

		for (int i = 0; i < 5; i++) {
			User user = new User();
			// user.setLogin_name(String.valueOf(9000+(10-i)));
			user.setName("user" + (i + 5));
			// 更新用户首选是根据email 或者 login_name 的值作为查找指定用户的关键字
			// 其次是emp_code或者cellvoice1 最后是id
			user.setEmail("user" + i + "@" + networkName);
			user.setPassword("222222");
			// user.setCellvoice2("1");
			user.setDeptCode(deptPrefix + "9"); // 对应Department的dept_code,这样可以创建用户到指定部门
			user.setEmpCode(String.valueOf(9000 + i + 5));
			user.setDisplay_order(String.valueOf(10 - i));
			user.setWorkvoice("11888871");
			if (all)
				user.setNetworkName(networkName);

			try {
				account.updateUser(user);
			} catch (ApiErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void testAdd10Dept(AppAccount account) {

		try {
			for (int i = 0; i < 10; i++) {
				Department dept = new Department();
				dept.setDept_code(deptPrefix + i);
				dept.setDisplay_order(String.valueOf(i));
				// dept.setParent_dept_code("11");
				if (all)
					dept.setNetwork_name(networkName);
				dept.setShortName("子公司" + i);

				account.createDepartment(dept);

			}
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate10Dept(AppAccount account) {

		try {
			for (int i = 0; i < 10; i++) {
				Department dept = new Department();
				dept.setDept_code(deptPrefix + i);
				dept.setDisplay_order(String.valueOf(10 - i));
				// dept.setParent_dept_code("11");
				if (all)
					dept.setNetwork_name(networkName);
				dept.setShortName("全资子公司" + i);

				account.updateDepartment(dept);

			}
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete10Dept(AppAccount account) {

		try {
			for (int i = 0; i < 10; i++) {
				Department dept = new Department();
				dept.setDept_code(deptPrefix + i);
				if (all)
					dept.setNetwork_name(networkName);

				account.deleteDepartment(dept.getDept_code(), true);

			}

		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testAdd10User(AppAccount account) {

		try {
			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setLoginName("9000");
				user.setName("user" + i);
				user.setEmail("user" + i + "@" + networkName);
				user.setPassword("111111");
				user.setTitle("经理");
				user.setCellvoice1("1");
				user.setCellvoice2("1");
				user.setWorkvoice("1");
				user.setDeptCode(deptPrefix + i); // 对应Department的dept_code,这样可以创建用户到指定部门
				user.setEmpCode("9000");
				user.setDisplay_order(String.valueOf(i));
				user.setExt1("ext");
				if (all)
					user.setNetworkName(networkName);

				account.addNewUser(user);

			}

		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdate10User(AppAccount account) {

		try {
			for (int i = 0; i < 10; i++) {
				User user = new User();
				// user.setLogin_name(String.valueOf(9000+(10-i)));
				user.setName("user000" + i);
				// 更新用户首选是根据email 或者 login_name 的值作为查找指定用户的关键字
				// 其次是emp_code或者cellvoice1 最后是id
				user.setEmail("user000" + i + "@" + networkName);
				user.setPassword("222222");
				user.setTitle("经理1");
				user.setCellvoice1("13900000000");
				user.setCellvoice2("13911111111");
				user.setDeptCode(deptPrefix + "9"); // 对应Department的dept_code,这样可以创建用户到指定部门
				user.setEmpCode(String.valueOf(9000 + i));
				user.setDisplay_order(String.valueOf(10 - i));
				user.setHidden("1");
				user.setWorkvoice("99999888");
				user.setExt1("ext1");
				user.setExt2("ext2");
				user.setExt3("ext3");
				user.setExt4("ext4");
				user.setExt5("ext5");
				user.setExt6("ext6");
				user.setExt7("ext7");
				user.setExt8("ext8");
				user.setExt9("ext9");
				user.setExt10("ext10");

				if (all)
					user.setNetworkName(networkName);

				account.updateUser(user);

			}

		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete10User(AppAccount account) {
		try {
			for (int i = 0; i < 10; i++) {
				User user = new User();
				if (all)
					user.setNetworkName(networkName);

				account.deleteUser(user);

			}

		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	// ======== 以下单部门测试 =========

	public static void testAddDepartment(AppAccount account) {

		try {
			Department dept = new Department();
			dept.setDept_code("001011014");
			dept.setDisplay_order("12");
			// dept.setParent_dept_code("11");
			dept.setShortName("微软公司");

			// dept.setFull_name(full_name);
			if (all)
				dept.setNetwork_name(networkName);

			account.createDepartment(dept);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdateDepartment(AppAccount account) {

		Department dept = new Department();
		dept.setDept_code("001012001"); // 该字段用于查询更新
		// dept.setDisplay_order("14");
		dept.setParent_dept_code("10");
		// dept.setShort_name("青岛");
		if (all)
			dept.setNetwork_name(networkName);
		// dept.setFull_name(full_name);
		// dept.setNetwork_id(network_id);

		try {
			account.updateDepartment(dept);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testDeleteDepartment(AppAccount account) {
		Department dept = new Department();
		dept.setDept_code("001011012"); // 该字段用于查询
		if (all)
			dept.setNetwork_name(networkName);

		try {
			account.deleteDepartment("001011012", false);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testAddUser(AppAccount account) {
		User user = new User();
		user.setLoginName("9000");
		user.setName("jobs");
		user.setEmail("jobs@htsc.view.cn");
		user.setPassword("111111");
		user.setTitle("经理");
		user.setCellvoice1("13211111111");
		user.setCellvoice2("13211111112");
		user.setDeptCode("001011012"); // 对应Department的dept_code,这样可以创建用户到指定部门
		user.setEmpCode("9000");
		user.setDisplay_order("100");
		if (all)
			user.setNetworkName(networkName);

		try {
			account.addNewUser(user);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdateUser(AppAccount account) {

		User user = account.findUserByLoginname("APP_test4");

		// user.setLogin_name("jg");
		user.setName("cuke");
		// user.setEmail("cuke@htsc.view.cn");
		// user.setPassword("111111");
		// user.setTitle("cuke经理");
		user.setCellvoice1("13222222222");
		user.setCellvoice2("13111111111");
		user.setWorkvoice("11111111");
		// user.setEmp_code("9000");
		// user.setDisplay_order("200");
		// if(all)
		// user.setNetwork_name(networkName);

		try {
			account.updateUser(user);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}

	}

	public static void testGenerateToken(AppAccount account)
			throws MxException, JSONException {

		String token = account.createMXSSOToken("58_0001"); // 创建用户58_001的mx_sso_token.
		System.out.println("mx_sso_token:" + token);
	}

	public static void testAddNetworks(AppAccount account) {
		Network n = new Network();
		n.setName("dehui.com");
		n.setDisplay_name("德惠众合");

		try {
			account.createNetwork(n);
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testUpdateNetworks(AppAccount account) {
		Network n = new Network();
		n.setName("dehui.com");// 根据社区name，查询后来更新社区显示名称
		n.setDisplay_name("德惠");

		try {
			account.updateNetwork(n);
			System.out.println("更新社区显示名称成功！");
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

	public static void testRemoveNetworks(AppAccount account) {

		try {
			account.deleteNetwork("dehui.com");
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
