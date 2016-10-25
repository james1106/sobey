package com.magic.sangha.util;

public class StatusConstant {
	/**δ���*/
	public static final Integer NONPASS = 0;
	/**���ͨ��*/
	public static final Integer PASS = 1;
	/**���δͨ��*/
	public static final Integer NOTPASS = 2;
	/**�˻������� ��״̬*/
	public static final Integer FROZEN = 3;
	
	/**��Ȩ��*/
	public static final Integer ALLOW = 0x1;
	/**û��Ȩ��*/
	public static final Integer NON_ALLOW = 0x2;
	
	// �������
	/**��ȡ�ɹ�*/
	public static final Integer SUCCESS_CODE = 200;
	/**��ȡʧ��*/
	public static final Integer Fail_CODE = 202;
	/**û��Ȩ�� �������*/
	public static final Integer NOT_AGREE = 1001;
	/**���󲻴���*/
	public static final Integer OBJECT_NOT_EXIST = 1002;
	/**�ֶβ���Ϊ��*/
	public static final Integer FIELD_NOT_NULL= 1003;
	/**�������*/
	public static final Integer PENDING = 1004;
	/**δ��¼*/
	public static final Integer NOTLOGIN= 1005;
	/**û������*/
	public static final Integer  NO_DATA = 1006;
	/**�˻�������*/
	public static final Integer ACCOUNT_FROZEN = 1007;
	
	/**������Ч*/
	public static final Integer ORDER_INVALID = 1008;
	
	/**����״̬�쳣*/
	public static final Integer ORDER_STATUS_ABNORMITY = 1009;
	
	/**��¼ʱ���ʺű�����״̬���ش���*/
	public static final Integer LOGIN_ACCOUNT = 1010;
	
	// ��ݱ�ʶ
	/**��ͨ�û�*/
	public static final String USER = "user";
	/**���´�Ա��*/
	public static final String GROUP_USER = "group_user";
	/**�ܹ�˾Ա��*/
	public static final String HEAD_USER = "head_user";
	/**�ֹ�˾Ա��*/
	public static final String FILIALE_USER = "filiale_user";
	
	
	
	/**�ӹ�˾*/
	public static final Integer FILIALE = 1;
	/**�ܹ�˾*/
	public static final Integer PARENT_COMPANY = 0;
	/**���´�*/
	public static final Integer OFFICE_COMPANY = 2;
	
	//**�������
	
	/**�����*/
	public static final Integer SOFTWARE = 0;
	/**Ӳ����*/
	public static final Integer HARDWARE = 1;
	
	// �Ƿ����֪ͨ�ʼ�
	
	/**�����ʼ�֪ͨ*/
	public static final Integer ACCEPT_EMAIL = 1;
	
	/**�������ʼ�֪ͨ*/
	public static final Integer NON_ACCEPT_EMAIL = 0;
	
	// �豸����
	/**android*/
	public static final Integer ANDROID=0;
	/**ios*/
	public static final Integer IOS = 1;
	
	/** ���Ϸ���*/
	public static final Integer UP = 0;
	/**���·���*/
	public static final Integer DOWN = 1;
	/**�缶����*/
	public static final Integer UPORDOWNWARD = 2;
	/**�û�׷������ ȫ�ֿɼ�*/
	public static final Integer ORDER_DECRIBE_USER = 3;
	
	/**�����Ѿ�ǩ��*/
	public static final Integer SIGNED = 0;
	/**����û��ǩ��*/
	public static final Integer NON_SIGNED = 1;
	
	/**isURL�ֶ�   ����ֱ�� ��Ҫ����������ϸҳ*/
	public static final Integer NON_URL = 0;
	/**isURL�ֶ� ֱ����ת��ָ����URL*/
	public static final Integer URL = 1;
	
	/**û��ͶƱ*/
	public static final Integer VOTE = 0;
	/**��ͶƱ*/
	public static final Integer NON_VOVE = 1;
	
	 /**ע������ ��������*/
    public static final String REGISTER_TYPE_NON = "0";
     /**ע������ �û�*/
     public static final String REGISTER_TYPE_USER = "1";
     /**ע������ �ܹ�˾Ա��*/
     public static final String REGISTER_TYPE_HEAD_EMPLOYEE = "2";
     /**ע������ �ֹ�˾Ա��*/
     public static final String REGISTER_TYPE_BRANCH_EMPLOYEE = "3";
     /**ע������ ���´�Ա��*/
     public static final String REGISTER_TYPE_OFFICE_EMPLOYEE = "4";
     
     /**ϵͳ��Ϣ*/
     public static final Integer SYSTEM_INFO = 0;
     /**������Ϣ*/
     public static final Integer ORDER_INFO = 1;
     
     // �ͷ�״̬
     /**����*/
     public static final Integer ONLINE = 0;
     /**����*/
     public static final Integer UNLINE = 1;
     
     // banner�����Ƿ����ڲ�����
     /**�������ӵ��ڲ�����*/
     public static final Integer NON_INSIDE = 0;
     /**���ڲ�����*/
     public static final Integer INSIDE = 1;
     
     // ��������
     /**ǩ������*/
     public static final Integer BONUS_TYPE_SIGN = 0;
     /**���ۻ���*/
     public static final Integer BONUS_TYPE_COMMENT = 1;
     
     // �û����� Ա������ 0 �ܹ�˾ 1 �ֹ�˾ 2 ���´�
     
     /**�ܹ�˾ Ա��*/
     public static final Integer HEAD_COMPLANY = 0;
     /**�ֹ�˾ Ա��*/
     public static final Integer BRANCH_OFFICE = 1;
     /**���´� Ա��*/
     public static final Integer OFFICE = 2;

}
