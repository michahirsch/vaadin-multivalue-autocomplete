/*
 * generated by Xtext 2.10.0
 */
package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.xtext.example.mydsl.myDsl.Argument;
import org.xtext.example.mydsl.myDsl.Condition;
import org.xtext.example.mydsl.myDsl.Module;
import org.xtext.example.mydsl.myDsl.MyDslPackage;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;

@SuppressWarnings("all")
public class MyDslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private MyDslGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == MyDslPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case MyDslPackage.ARGUMENT:
				sequence_Argument(context, (Argument) semanticObject); 
				return; 
			case MyDslPackage.CONDITION:
				sequence_Condition(context, (Condition) semanticObject); 
				return; 
			case MyDslPackage.MODULE:
				sequence_Module(context, (Module) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Argument returns Argument
	 *
	 * Constraint:
	 *     (string=STRING | int=INT)
	 */
	protected void sequence_Argument(ISerializationContext context, Argument semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Condition returns Condition
	 *
	 * Constraint:
	 *     (
	 *         (
	 *             left='name' | 
	 *             left='description' | 
	 *             left='controllerId' | 
	 *             left='updatestatus' | 
	 *             left='ipaddress' | 
	 *             left='attribute' | 
	 *             left='assigndds' | 
	 *             left='installedds' | 
	 *             left='tag' | 
	 *             left='lastcontrollerrequestat'
	 *         ) 
	 *         (
	 *             op='==' | 
	 *             op='!=' | 
	 *             op='>' | 
	 *             op='<' | 
	 *             op='>=' | 
	 *             op='<=' | 
	 *             op='=le=' | 
	 *             op='=ge=' | 
	 *             op='=lt=' | 
	 *             op='=gt=' | 
	 *             op='=in=' | 
	 *             op='=out='
	 *         ) 
	 *         right=Argument
	 *     )
	 */
	protected void sequence_Condition(ISerializationContext context, Condition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Module returns Module
	 *
	 * Constraint:
	 *     (
	 *         conditions+=Condition 
	 *         (
	 *             (
	 *                 op+='&&' | 
	 *                 op+='and' | 
	 *                 op+='||' | 
	 *                 op+='or' | 
	 *                 op+=';' | 
	 *                 op+=','
	 *             ) 
	 *             conditions+=Condition
	 *         )*
	 *     )
	 */
	protected void sequence_Module(ISerializationContext context, Module semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}