package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_INT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'&&'", "'and'", "'||'", "'or'", "';'", "','", "'name'", "'description'", "'controllerId'", "'updatestatus'", "'ipaddress'", "'attribute'", "'assigndds'", "'installedds'", "'tag'", "'lastcontrollerrequestat'", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'=le='", "'=ge='", "'=lt='", "'=gt='", "'=in='", "'=out='"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=6;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }



     	private MyDslGrammarAccess grammarAccess;

        public InternalMyDslParser(TokenStream input, MyDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Module";
       	}

       	@Override
       	protected MyDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModule"
    // InternalMyDsl.g:64:1: entryRuleModule returns [EObject current=null] : iv_ruleModule= ruleModule EOF ;
    public final EObject entryRuleModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModule = null;


        try {
            // InternalMyDsl.g:64:47: (iv_ruleModule= ruleModule EOF )
            // InternalMyDsl.g:65:2: iv_ruleModule= ruleModule EOF
            {
             newCompositeNode(grammarAccess.getModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModule=ruleModule();

            state._fsp--;

             current =iv_ruleModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModule"


    // $ANTLR start "ruleModule"
    // InternalMyDsl.g:71:1: ruleModule returns [EObject current=null] : ( ( (lv_conditions_0_0= ruleCondition ) ) ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )* ) ;
    public final EObject ruleModule() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        Token lv_op_1_4=null;
        Token lv_op_1_5=null;
        Token lv_op_1_6=null;
        EObject lv_conditions_0_0 = null;

        EObject lv_conditions_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:77:2: ( ( ( (lv_conditions_0_0= ruleCondition ) ) ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )* ) )
            // InternalMyDsl.g:78:2: ( ( (lv_conditions_0_0= ruleCondition ) ) ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )* )
            {
            // InternalMyDsl.g:78:2: ( ( (lv_conditions_0_0= ruleCondition ) ) ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )* )
            // InternalMyDsl.g:79:3: ( (lv_conditions_0_0= ruleCondition ) ) ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )*
            {
            // InternalMyDsl.g:79:3: ( (lv_conditions_0_0= ruleCondition ) )
            // InternalMyDsl.g:80:4: (lv_conditions_0_0= ruleCondition )
            {
            // InternalMyDsl.g:80:4: (lv_conditions_0_0= ruleCondition )
            // InternalMyDsl.g:81:5: lv_conditions_0_0= ruleCondition
            {

            					newCompositeNode(grammarAccess.getModuleAccess().getConditionsConditionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_3);
            lv_conditions_0_0=ruleCondition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModuleRule());
            					}
            					add(
            						current,
            						"conditions",
            						lv_conditions_0_0,
            						"org.xtext.example.mydsl.MyDsl.Condition");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:98:3: ( ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=11 && LA2_0<=16)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMyDsl.g:99:4: ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) ) ( (lv_conditions_2_0= ruleCondition ) )
            	    {
            	    // InternalMyDsl.g:99:4: ( ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) ) )
            	    // InternalMyDsl.g:100:5: ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) )
            	    {
            	    // InternalMyDsl.g:100:5: ( (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' ) )
            	    // InternalMyDsl.g:101:6: (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' )
            	    {
            	    // InternalMyDsl.g:101:6: (lv_op_1_1= '&&' | lv_op_1_2= 'and' | lv_op_1_3= '||' | lv_op_1_4= 'or' | lv_op_1_5= ';' | lv_op_1_6= ',' )
            	    int alt1=6;
            	    switch ( input.LA(1) ) {
            	    case 11:
            	        {
            	        alt1=1;
            	        }
            	        break;
            	    case 12:
            	        {
            	        alt1=2;
            	        }
            	        break;
            	    case 13:
            	        {
            	        alt1=3;
            	        }
            	        break;
            	    case 14:
            	        {
            	        alt1=4;
            	        }
            	        break;
            	    case 15:
            	        {
            	        alt1=5;
            	        }
            	        break;
            	    case 16:
            	        {
            	        alt1=6;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 1, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt1) {
            	        case 1 :
            	            // InternalMyDsl.g:102:7: lv_op_1_1= '&&'
            	            {
            	            lv_op_1_1=(Token)match(input,11,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_1, grammarAccess.getModuleAccess().getOpAmpersandAmpersandKeyword_1_0_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalMyDsl.g:113:7: lv_op_1_2= 'and'
            	            {
            	            lv_op_1_2=(Token)match(input,12,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_2, grammarAccess.getModuleAccess().getOpAndKeyword_1_0_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalMyDsl.g:124:7: lv_op_1_3= '||'
            	            {
            	            lv_op_1_3=(Token)match(input,13,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_3, grammarAccess.getModuleAccess().getOpVerticalLineVerticalLineKeyword_1_0_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalMyDsl.g:135:7: lv_op_1_4= 'or'
            	            {
            	            lv_op_1_4=(Token)match(input,14,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_4, grammarAccess.getModuleAccess().getOpOrKeyword_1_0_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_4, null);
            	            						

            	            }
            	            break;
            	        case 5 :
            	            // InternalMyDsl.g:146:7: lv_op_1_5= ';'
            	            {
            	            lv_op_1_5=(Token)match(input,15,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_5, grammarAccess.getModuleAccess().getOpSemicolonKeyword_1_0_0_4());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_5, null);
            	            						

            	            }
            	            break;
            	        case 6 :
            	            // InternalMyDsl.g:157:7: lv_op_1_6= ','
            	            {
            	            lv_op_1_6=(Token)match(input,16,FOLLOW_4); 

            	            							newLeafNode(lv_op_1_6, grammarAccess.getModuleAccess().getOpCommaKeyword_1_0_0_5());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getModuleRule());
            	            							}
            	            							addWithLastConsumed(current, "op", lv_op_1_6, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalMyDsl.g:170:4: ( (lv_conditions_2_0= ruleCondition ) )
            	    // InternalMyDsl.g:171:5: (lv_conditions_2_0= ruleCondition )
            	    {
            	    // InternalMyDsl.g:171:5: (lv_conditions_2_0= ruleCondition )
            	    // InternalMyDsl.g:172:6: lv_conditions_2_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getModuleAccess().getConditionsConditionParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_conditions_2_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getModuleRule());
            	    						}
            	    						add(
            	    							current,
            	    							"conditions",
            	    							lv_conditions_2_0,
            	    							"org.xtext.example.mydsl.MyDsl.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModule"


    // $ANTLR start "entryRuleCondition"
    // InternalMyDsl.g:194:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalMyDsl.g:194:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalMyDsl.g:195:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalMyDsl.g:201:1: ruleCondition returns [EObject current=null] : ( ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) ) ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) ) ( (lv_right_2_0= ruleArgument ) ) ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        Token lv_left_0_1=null;
        Token lv_left_0_2=null;
        Token lv_left_0_3=null;
        Token lv_left_0_4=null;
        Token lv_left_0_5=null;
        Token lv_left_0_6=null;
        Token lv_left_0_7=null;
        Token lv_left_0_8=null;
        Token lv_left_0_9=null;
        Token lv_left_0_10=null;
        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        Token lv_op_1_4=null;
        Token lv_op_1_5=null;
        Token lv_op_1_6=null;
        Token lv_op_1_7=null;
        Token lv_op_1_8=null;
        Token lv_op_1_9=null;
        Token lv_op_1_10=null;
        Token lv_op_1_11=null;
        Token lv_op_1_12=null;
        EObject lv_right_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:207:2: ( ( ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) ) ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) ) ( (lv_right_2_0= ruleArgument ) ) ) )
            // InternalMyDsl.g:208:2: ( ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) ) ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) ) ( (lv_right_2_0= ruleArgument ) ) )
            {
            // InternalMyDsl.g:208:2: ( ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) ) ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) ) ( (lv_right_2_0= ruleArgument ) ) )
            // InternalMyDsl.g:209:3: ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) ) ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) ) ( (lv_right_2_0= ruleArgument ) )
            {
            // InternalMyDsl.g:209:3: ( ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) ) )
            // InternalMyDsl.g:210:4: ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) )
            {
            // InternalMyDsl.g:210:4: ( (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' ) )
            // InternalMyDsl.g:211:5: (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' )
            {
            // InternalMyDsl.g:211:5: (lv_left_0_1= 'name' | lv_left_0_2= 'description' | lv_left_0_3= 'controllerId' | lv_left_0_4= 'updatestatus' | lv_left_0_5= 'ipaddress' | lv_left_0_6= 'attribute' | lv_left_0_7= 'assigndds' | lv_left_0_8= 'installedds' | lv_left_0_9= 'tag' | lv_left_0_10= 'lastcontrollerrequestat' )
            int alt3=10;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt3=1;
                }
                break;
            case 18:
                {
                alt3=2;
                }
                break;
            case 19:
                {
                alt3=3;
                }
                break;
            case 20:
                {
                alt3=4;
                }
                break;
            case 21:
                {
                alt3=5;
                }
                break;
            case 22:
                {
                alt3=6;
                }
                break;
            case 23:
                {
                alt3=7;
                }
                break;
            case 24:
                {
                alt3=8;
                }
                break;
            case 25:
                {
                alt3=9;
                }
                break;
            case 26:
                {
                alt3=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalMyDsl.g:212:6: lv_left_0_1= 'name'
                    {
                    lv_left_0_1=(Token)match(input,17,FOLLOW_5); 

                    						newLeafNode(lv_left_0_1, grammarAccess.getConditionAccess().getLeftNameKeyword_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:223:6: lv_left_0_2= 'description'
                    {
                    lv_left_0_2=(Token)match(input,18,FOLLOW_5); 

                    						newLeafNode(lv_left_0_2, grammarAccess.getConditionAccess().getLeftDescriptionKeyword_0_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:234:6: lv_left_0_3= 'controllerId'
                    {
                    lv_left_0_3=(Token)match(input,19,FOLLOW_5); 

                    						newLeafNode(lv_left_0_3, grammarAccess.getConditionAccess().getLeftControllerIdKeyword_0_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:245:6: lv_left_0_4= 'updatestatus'
                    {
                    lv_left_0_4=(Token)match(input,20,FOLLOW_5); 

                    						newLeafNode(lv_left_0_4, grammarAccess.getConditionAccess().getLeftUpdatestatusKeyword_0_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_4, null);
                    					

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:256:6: lv_left_0_5= 'ipaddress'
                    {
                    lv_left_0_5=(Token)match(input,21,FOLLOW_5); 

                    						newLeafNode(lv_left_0_5, grammarAccess.getConditionAccess().getLeftIpaddressKeyword_0_0_4());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_5, null);
                    					

                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:267:6: lv_left_0_6= 'attribute'
                    {
                    lv_left_0_6=(Token)match(input,22,FOLLOW_5); 

                    						newLeafNode(lv_left_0_6, grammarAccess.getConditionAccess().getLeftAttributeKeyword_0_0_5());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_6, null);
                    					

                    }
                    break;
                case 7 :
                    // InternalMyDsl.g:278:6: lv_left_0_7= 'assigndds'
                    {
                    lv_left_0_7=(Token)match(input,23,FOLLOW_5); 

                    						newLeafNode(lv_left_0_7, grammarAccess.getConditionAccess().getLeftAssignddsKeyword_0_0_6());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_7, null);
                    					

                    }
                    break;
                case 8 :
                    // InternalMyDsl.g:289:6: lv_left_0_8= 'installedds'
                    {
                    lv_left_0_8=(Token)match(input,24,FOLLOW_5); 

                    						newLeafNode(lv_left_0_8, grammarAccess.getConditionAccess().getLeftInstalleddsKeyword_0_0_7());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_8, null);
                    					

                    }
                    break;
                case 9 :
                    // InternalMyDsl.g:300:6: lv_left_0_9= 'tag'
                    {
                    lv_left_0_9=(Token)match(input,25,FOLLOW_5); 

                    						newLeafNode(lv_left_0_9, grammarAccess.getConditionAccess().getLeftTagKeyword_0_0_8());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_9, null);
                    					

                    }
                    break;
                case 10 :
                    // InternalMyDsl.g:311:6: lv_left_0_10= 'lastcontrollerrequestat'
                    {
                    lv_left_0_10=(Token)match(input,26,FOLLOW_5); 

                    						newLeafNode(lv_left_0_10, grammarAccess.getConditionAccess().getLeftLastcontrollerrequestatKeyword_0_0_9());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "left", lv_left_0_10, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalMyDsl.g:324:3: ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) ) )
            // InternalMyDsl.g:325:4: ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) )
            {
            // InternalMyDsl.g:325:4: ( (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' ) )
            // InternalMyDsl.g:326:5: (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' )
            {
            // InternalMyDsl.g:326:5: (lv_op_1_1= '==' | lv_op_1_2= '!=' | lv_op_1_3= '>' | lv_op_1_4= '<' | lv_op_1_5= '>=' | lv_op_1_6= '<=' | lv_op_1_7= '=le=' | lv_op_1_8= '=ge=' | lv_op_1_9= '=lt=' | lv_op_1_10= '=gt=' | lv_op_1_11= '=in=' | lv_op_1_12= '=out=' )
            int alt4=12;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt4=1;
                }
                break;
            case 28:
                {
                alt4=2;
                }
                break;
            case 29:
                {
                alt4=3;
                }
                break;
            case 30:
                {
                alt4=4;
                }
                break;
            case 31:
                {
                alt4=5;
                }
                break;
            case 32:
                {
                alt4=6;
                }
                break;
            case 33:
                {
                alt4=7;
                }
                break;
            case 34:
                {
                alt4=8;
                }
                break;
            case 35:
                {
                alt4=9;
                }
                break;
            case 36:
                {
                alt4=10;
                }
                break;
            case 37:
                {
                alt4=11;
                }
                break;
            case 38:
                {
                alt4=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalMyDsl.g:327:6: lv_op_1_1= '=='
                    {
                    lv_op_1_1=(Token)match(input,27,FOLLOW_6); 

                    						newLeafNode(lv_op_1_1, grammarAccess.getConditionAccess().getOpEqualsSignEqualsSignKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:338:6: lv_op_1_2= '!='
                    {
                    lv_op_1_2=(Token)match(input,28,FOLLOW_6); 

                    						newLeafNode(lv_op_1_2, grammarAccess.getConditionAccess().getOpExclamationMarkEqualsSignKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:349:6: lv_op_1_3= '>'
                    {
                    lv_op_1_3=(Token)match(input,29,FOLLOW_6); 

                    						newLeafNode(lv_op_1_3, grammarAccess.getConditionAccess().getOpGreaterThanSignKeyword_1_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:360:6: lv_op_1_4= '<'
                    {
                    lv_op_1_4=(Token)match(input,30,FOLLOW_6); 

                    						newLeafNode(lv_op_1_4, grammarAccess.getConditionAccess().getOpLessThanSignKeyword_1_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_4, null);
                    					

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:371:6: lv_op_1_5= '>='
                    {
                    lv_op_1_5=(Token)match(input,31,FOLLOW_6); 

                    						newLeafNode(lv_op_1_5, grammarAccess.getConditionAccess().getOpGreaterThanSignEqualsSignKeyword_1_0_4());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_5, null);
                    					

                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:382:6: lv_op_1_6= '<='
                    {
                    lv_op_1_6=(Token)match(input,32,FOLLOW_6); 

                    						newLeafNode(lv_op_1_6, grammarAccess.getConditionAccess().getOpLessThanSignEqualsSignKeyword_1_0_5());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_6, null);
                    					

                    }
                    break;
                case 7 :
                    // InternalMyDsl.g:393:6: lv_op_1_7= '=le='
                    {
                    lv_op_1_7=(Token)match(input,33,FOLLOW_6); 

                    						newLeafNode(lv_op_1_7, grammarAccess.getConditionAccess().getOpLeKeyword_1_0_6());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_7, null);
                    					

                    }
                    break;
                case 8 :
                    // InternalMyDsl.g:404:6: lv_op_1_8= '=ge='
                    {
                    lv_op_1_8=(Token)match(input,34,FOLLOW_6); 

                    						newLeafNode(lv_op_1_8, grammarAccess.getConditionAccess().getOpGeKeyword_1_0_7());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_8, null);
                    					

                    }
                    break;
                case 9 :
                    // InternalMyDsl.g:415:6: lv_op_1_9= '=lt='
                    {
                    lv_op_1_9=(Token)match(input,35,FOLLOW_6); 

                    						newLeafNode(lv_op_1_9, grammarAccess.getConditionAccess().getOpLtKeyword_1_0_8());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_9, null);
                    					

                    }
                    break;
                case 10 :
                    // InternalMyDsl.g:426:6: lv_op_1_10= '=gt='
                    {
                    lv_op_1_10=(Token)match(input,36,FOLLOW_6); 

                    						newLeafNode(lv_op_1_10, grammarAccess.getConditionAccess().getOpGtKeyword_1_0_9());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_10, null);
                    					

                    }
                    break;
                case 11 :
                    // InternalMyDsl.g:437:6: lv_op_1_11= '=in='
                    {
                    lv_op_1_11=(Token)match(input,37,FOLLOW_6); 

                    						newLeafNode(lv_op_1_11, grammarAccess.getConditionAccess().getOpInKeyword_1_0_10());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_11, null);
                    					

                    }
                    break;
                case 12 :
                    // InternalMyDsl.g:448:6: lv_op_1_12= '=out='
                    {
                    lv_op_1_12=(Token)match(input,38,FOLLOW_6); 

                    						newLeafNode(lv_op_1_12, grammarAccess.getConditionAccess().getOpOutKeyword_1_0_11());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getConditionRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_12, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalMyDsl.g:461:3: ( (lv_right_2_0= ruleArgument ) )
            // InternalMyDsl.g:462:4: (lv_right_2_0= ruleArgument )
            {
            // InternalMyDsl.g:462:4: (lv_right_2_0= ruleArgument )
            // InternalMyDsl.g:463:5: lv_right_2_0= ruleArgument
            {

            					newCompositeNode(grammarAccess.getConditionAccess().getRightArgumentParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_right_2_0=ruleArgument();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_2_0,
            						"org.xtext.example.mydsl.MyDsl.Argument");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleArgument"
    // InternalMyDsl.g:484:1: entryRuleArgument returns [EObject current=null] : iv_ruleArgument= ruleArgument EOF ;
    public final EObject entryRuleArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArgument = null;


        try {
            // InternalMyDsl.g:484:49: (iv_ruleArgument= ruleArgument EOF )
            // InternalMyDsl.g:485:2: iv_ruleArgument= ruleArgument EOF
            {
             newCompositeNode(grammarAccess.getArgumentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArgument=ruleArgument();

            state._fsp--;

             current =iv_ruleArgument; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArgument"


    // $ANTLR start "ruleArgument"
    // InternalMyDsl.g:491:1: ruleArgument returns [EObject current=null] : ( ( (lv_string_0_0= RULE_STRING ) ) | ( (lv_int_1_0= RULE_INT ) ) ) ;
    public final EObject ruleArgument() throws RecognitionException {
        EObject current = null;

        Token lv_string_0_0=null;
        Token lv_int_1_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:497:2: ( ( ( (lv_string_0_0= RULE_STRING ) ) | ( (lv_int_1_0= RULE_INT ) ) ) )
            // InternalMyDsl.g:498:2: ( ( (lv_string_0_0= RULE_STRING ) ) | ( (lv_int_1_0= RULE_INT ) ) )
            {
            // InternalMyDsl.g:498:2: ( ( (lv_string_0_0= RULE_STRING ) ) | ( (lv_int_1_0= RULE_INT ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_STRING) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_INT) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalMyDsl.g:499:3: ( (lv_string_0_0= RULE_STRING ) )
                    {
                    // InternalMyDsl.g:499:3: ( (lv_string_0_0= RULE_STRING ) )
                    // InternalMyDsl.g:500:4: (lv_string_0_0= RULE_STRING )
                    {
                    // InternalMyDsl.g:500:4: (lv_string_0_0= RULE_STRING )
                    // InternalMyDsl.g:501:5: lv_string_0_0= RULE_STRING
                    {
                    lv_string_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    					newLeafNode(lv_string_0_0, grammarAccess.getArgumentAccess().getStringSTRINGTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getArgumentRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"string",
                    						lv_string_0_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:518:3: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // InternalMyDsl.g:518:3: ( (lv_int_1_0= RULE_INT ) )
                    // InternalMyDsl.g:519:4: (lv_int_1_0= RULE_INT )
                    {
                    // InternalMyDsl.g:519:4: (lv_int_1_0= RULE_INT )
                    // InternalMyDsl.g:520:5: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    					newLeafNode(lv_int_1_0, grammarAccess.getArgumentAccess().getIntINTTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getArgumentRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"int",
                    						lv_int_1_0,
                    						"org.eclipse.xtext.common.Terminals.INT");
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArgument"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000001F802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000007FE0000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000007FF8000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000030L});

}