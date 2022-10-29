#include <functions.c>
login()
{
	lr_start_transaction("UC01_TR01_mainPage_Open");
	
	mainPage_Open();

	lr_end_transaction("UC01_TR01_mainPage_Open",LR_AUTO);
	
	
	lr_think_time(39);
	
	
	web_reg_save_param_regexp (
    	"ParamName=csrfmiddlewaretoken",
    	"RegExp=n\" value=\"(.*)\">",
    	"Ordinal=1",
		SEARCH_FILTERS,
		LAST );
	
	
	lr_start_transaction("UC01_TR02_loginPage_Open");

	loginPage_Open();
	
	lr_end_transaction("UC01_TR02_loginPage_Open",LR_AUTO);

	
	web_reg_find("Text=logout",
        "SaveCount=logout_Count",
        LAST );
	
	
	web_reg_save_param_regexp (
    	"ParamName=value",
    	"RegExp=d\' value=\'(.*?)'\/\>",
    	"Ordinal=1",
		SEARCH_FILTERS,
		LAST );
	
	lr_start_transaction("UC01_TR03_Login");
	
	web_submit_data("login",
		"Action={protocol}://{host}:{port}/login/",
		"Method=POST",
		"TargetFrame=",
		"RecContentType=text/html",
		"Referer={protocol}://{host}:{port}/login/?next=/",
		"Snapshot=t9.inf",
		"Mode=HTML",
		ITEMDATA,
		"Name=username", "Value={user_login}", ENDITEM,
		"Name=password", "Value={user_password}", ENDITEM,
		"Name=next", "Value=/", ENDITEM,
		"Name=csrfmiddlewaretoken", "Value={csrfmiddlewaretoken}", ENDITEM,
		LAST);
	
	lr_end_transaction("UC01_TR03_Login",LR_AUTO);
	
	if (atoi(lr_eval_string("{logout_Count}")) > 0){
    	lr_output_message("Log on successful.");
    }
    else{
    	lr_error_message("Log on failed");
        return(0);
    }

	lr_start_transaction("UC01_TR04_TicketList");
		
	TicketList();

	lr_end_transaction("UC01_TR04_TicketList",LR_AUTO);

	
	lr_start_transaction("UC01_TR05_Exit");

	Exit();

	lr_end_transaction("UC01_TR05_Exit",LR_AUTO);
	
	return 0;
}