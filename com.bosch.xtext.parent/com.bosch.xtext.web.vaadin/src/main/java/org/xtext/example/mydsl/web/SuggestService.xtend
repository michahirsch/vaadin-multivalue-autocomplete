package org.xtext.example.mydsl.web

import com.bosch.example.rsql.SuggestContext
import com.bosch.example.rsql.SuggestOracle
import com.bosch.example.rsql.TokenSuggestionContext
import java.util.ArrayList
import java.util.List
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistEntry
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor

class SuggestService implements SuggestContext, SuggestOracle{
	
	val List<ExecutorService> executorServices = newArrayList
	
	ExecutorService executorService
	
	XTextParsingService parsingService;
	
	TokenSuggestionContext context;
	
	new(XTextParsingService parsingService) {
		this.parsingService = parsingService
		executorService = Executors.newCachedThreadPool => [executorServices += it]
	}
	
	
	override getCursorContext() {
		context;
	}
	
	override getSyntaxErrorContext() {
		context;
	}
	
	override hasSyntaxError() {
		parsingService.hasSyntaxError()
		
	}
	
	override getSuggestion(String query, int cursorPos) {
		
		val proposals = new ArrayList
		
		val acceptor = new IIdeContentProposalAcceptor{
			
			override accept(ContentAssistEntry entry, int priority) {
				if(entry == null){
					return;
				}
				println(entry.proposal)
				proposals.add(entry.proposal);
			}
			
			override canAcceptMoreProposals() {
				return true;
			}
			
		}
		
		parsingService.createProposals(query, cursorPos, acceptor);
		
		
		
		context = new TokenSuggestionContext(0, 0 ,proposals);
		return this;
		
	}
	
	def ExecutorService getExecutorService() {
		return  executorService;
	}
	
  def void tearDown() {
		executorServices.forEach[shutdown()]
		executorServices.clear()
	}
	
}