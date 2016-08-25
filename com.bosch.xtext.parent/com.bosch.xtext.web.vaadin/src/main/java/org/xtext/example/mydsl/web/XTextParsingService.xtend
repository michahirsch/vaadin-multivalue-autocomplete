package org.xtext.example.mydsl.web

import com.google.inject.Inject
import com.google.inject.Provider
import java.util.concurrent.ExecutorService
import javax.inject.Singleton
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor
import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider
import org.eclipse.xtext.ide.editor.contentassist.antlr.ContentAssistContextFactory
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.resource.FileExtensionProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.util.ITextRegion
import org.eclipse.xtext.util.StringInputStream
import org.eclipse.xtext.util.TextRegion

@Singleton
class XTextParsingService{
	

	@Inject Provider<ContentAssistContextFactory> contextFactoryProvider
	
	@Inject IdeContentProposalProvider proposalProvider
	
	@Inject 
	ExecutorService executorService
	
	@Inject
	Provider<XtextResourceSet> rsp
	
	@Inject
	extension FileExtensionProvider
	
	XtextResource resource
	

	def void createProposals(String text, int cursorPosition, IIdeContentProposalAcceptor contenAssist) {
		loadResource(text);
		val contexts = resource.getContexts(new TextRegion(cursorPosition,0),text.length)
		 proposalProvider.createProposals(contexts, contenAssist)
		 hasSyntaxError
	}
	
	
	def void loadResource(String text){
		val rs = rsp.get
		resource = rs.createResource(URI.createURI("parser." + primaryFileExtension)) as XtextResource
		resource.load(new StringInputStream(text), null)
	}
	
    def boolean hasSyntaxError() {
    	 val result = resource.parseResult
    	 println(result.syntaxErrors.size)
    	 for(INode node : result.syntaxErrors){
    	 	println("startline" + node.startLine);
    	 	println("startline" + node.endLine);
    	 	print(node.syntaxErrorMessage)
    	 }
    	 
		 return result.hasSyntaxErrors;
	}
	
	
	def ContentAssistContext[] getContexts(XtextResource resource, ITextRegion selection, int caretOffset) {
		val text = resource.parseResult.rootNode.text
		if (caretOffset > text.length)
			return #[]
		val contextFactory = contextFactoryProvider.get() => [it.pool = executorService]
		contextFactory.create(text, selection, caretOffset, resource)
	}
	
}
