package org.xtext.example.mydsl.web;

import com.bosch.example.rsql.SuggestContext;
import com.bosch.example.rsql.SuggestOracle;
import com.bosch.example.rsql.TokenSuggestionContext;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistEntry;
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.xtext.example.mydsl.web.XTextParsingService;

@SuppressWarnings("all")
public class SuggestService implements SuggestContext, SuggestOracle {
  private final List<ExecutorService> executorServices = CollectionLiterals.<ExecutorService>newArrayList();
  
  private ExecutorService executorService;
  
  private XTextParsingService parsingService;
  
  private TokenSuggestionContext context;
  
  public SuggestService(final XTextParsingService parsingService) {
    this.parsingService = parsingService;
    ExecutorService _newCachedThreadPool = Executors.newCachedThreadPool();
    final Procedure1<ExecutorService> _function = (ExecutorService it) -> {
      this.executorServices.add(it);
    };
    ExecutorService _doubleArrow = ObjectExtensions.<ExecutorService>operator_doubleArrow(_newCachedThreadPool, _function);
    this.executorService = _doubleArrow;
  }
  
  @Override
  public TokenSuggestionContext getCursorContext() {
    return this.context;
  }
  
  @Override
  public TokenSuggestionContext getSyntaxErrorContext() {
    return this.context;
  }
  
  @Override
  public boolean hasSyntaxError() {
    return this.parsingService.hasSyntaxError();
  }
  
  @Override
  public SuggestContext getSuggestion(final String query, final int cursorPos) {
    final ArrayList<String> proposals = new ArrayList<String>();
    final IIdeContentProposalAcceptor acceptor = new IIdeContentProposalAcceptor() {
      @Override
      public void accept(final ContentAssistEntry entry, final int priority) {
        boolean _equals = Objects.equal(entry, null);
        if (_equals) {
          return;
        }
        String _proposal = entry.getProposal();
        InputOutput.<String>println(_proposal);
        String _proposal_1 = entry.getProposal();
        proposals.add(_proposal_1);
      }
      
      @Override
      public boolean canAcceptMoreProposals() {
        return true;
      }
    };
    this.parsingService.createProposals(query, cursorPos, acceptor);
    TokenSuggestionContext _tokenSuggestionContext = new TokenSuggestionContext(0, 0, proposals);
    this.context = _tokenSuggestionContext;
    return this;
  }
  
  public ExecutorService getExecutorService() {
    return this.executorService;
  }
  
  public void tearDown() {
    final Consumer<ExecutorService> _function = (ExecutorService it) -> {
      it.shutdown();
    };
    this.executorServices.forEach(_function);
    this.executorServices.clear();
  }
}
