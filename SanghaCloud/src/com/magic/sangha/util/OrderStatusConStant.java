package com.magic.sangha.util;

public class OrderStatusConStant {
	
	/**待处理*/
	public static final Integer SUSPENDING = 2001;
	
//	/**待办*/
//	public static final Integer BACKLOG = 3001;
	
//	public static final Integer 
	
	/**处理中*/
	public static final Integer HANDLING = 2002;
	
	/**待验证*/
	public static final Integer VERIFICATIONPENDING = 2003;
	
	/**待评价*/
	public static final Integer OVERALLPENGDING = 2004;
	
	/**已经解决，表示 已经评价后的状态*/
	public static final Integer SOLVED = 2005;
	
	/**进行中*/
	public static final Integer PENDING = 2006;
	
	
	/**已经查看过*/
	public static final Integer CHECKED = 1;
	
	/**未查看*/
	public static final Integer NON_CHECK = 0;
	
	/**客服 用于查询标记*/
	public static final Integer SERVICE_USER = 0;
	
	/**TSC技术人员 用于查询标记*/
	public static final Integer TECHTSC_USER = 1;
	
	// 订单跟踪话术
	/**提交订单 话术*/
	public static final String ORDER_SUBMITED = "您已提交订单，请等待客服受理。";
	
	/**订单已受理 话术*/
	public static final String ORDER_ACCEPTED = "您的订单已受理，正在为您分配技术人员。";
	
	/**技术人员开始处理  话术*/
	public static final String ORDER_STARTHANDLE = "技术人员开始处理，请您耐心等待。";
	
	/**订单被 技术完成的时候，用户订单流程 话术*/
	public static final String ORDER_VERIFY = "您的问题已经处理，请您验收。";
	
	/**订单处理完毕  话术*/
	public static final String ORDER_OVER = "您的订单已处理完毕。";
	
	/**结束语 话术*/
	public static final String ORDER_THX = "感谢您对索贝的信任支持，谢谢。";
	
	// 订单跟踪话术 员工版
	public static final String QUESTION_SUBMITED = "问题已提交，客服正在受理。";
	
	public static final String ALLOTING_TO_TECH = "客服已受理，正在分配技术支持人员。";
	
	public static final String ALLOTED_TO_TECH = "已分配技术支持人员{0}，正在处理。";
	
	public static final String ALLOTED_TO_HEAD_TECH = "已申请总部技术支持人员{0}协助，正在处理。";
	
	public static final String ALLOTED_TO_HEAD_DEVELOP = "已申请总部研发人员{0}协助，正在处理。";
	
	public static final String QUEST_VERIFI = "问题已处理完成，等待用户验证中。";
	
	public static final String QUEST_OVER = "该订单已处理完毕。";
	
	public static final String NO_PASS_ORDER = "该订单验收未通过。";
	
	
	
	/**有效订单*/
	public static final Integer VALIED = 0;
	/**无效订单*/
	public static final Integer NON_VALIED = 1;
	
	/**已经评价*/
	public static final Integer COMMENTED = 1;
	/**未评价*/
	public static final Integer NON_COMMENT = 0;
	/**没有达到评价的条件*/
	public static final Integer VALID_COMMENT = 2;
	
	/**没有直接分配给总部*/
	public static final Integer NON_HEADTECH = 0;
	/** 直接 分配给总部*/
	public static final Integer HEADTECH = 1;
	
	/**接受处理*/
	public static final Integer ACCEPT = 0;
	/**不接受处理*/
	public static final Integer NON_ACCEPT = 1;
	
	/**没有接受任务*/
	public static final Integer NON_ORDER_ACCEPT = 0;
	/**接受任务*/
	public static final Integer ORDER_ACCEPT = 1;
	/**接收任务的默认值*/
	public static final Integer DEFAULT_ORDER_ACCEPT = 2;
	
	/**已完成*/
	public static final Integer OVER = 0;
	/**未完成*/
	public static final Integer NON_OVER = 1;
	
	
	

}
