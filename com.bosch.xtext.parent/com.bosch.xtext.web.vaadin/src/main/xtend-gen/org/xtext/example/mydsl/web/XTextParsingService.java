package org.xtext.example.mydsl.web;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor;
import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider;
import org.eclipse.xtext.ide.editor.contentassist.antlr.ContentAssistContextFactory;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@Singleton
@SuppressWarnings("all")
public class XTextParsingService {
  @Inject
  private Provider<ContentAssistContextFactory> contextFactoryProvider;
  
  @Inject
  private IdeContentProposalProvider proposalProvider;
  
  @Inject
  private ExecutorService executorService;
  
  @Inject
  private Provider<XtextResourceSet> rsp;
  
  @Inject
  @Extension
  private FileExtensionProvider _fileExtensionProvider;
  
  private XtextResource resource;
  
  public void createProposals(final String text, final int cursorPosition, final IIdeContentProposalAcceptor contenAssist) {
    this.loadResource(text);
    TextRegion _textRegion = new TextRegion(cursorPosition, 0);
    int _length = text.length();
    final ContentAssistContext[] contexts = this.getContexts(this.resource, _textRegion, _length);
    this.proposalProvider.createProposals(((Collection<ContentAssistContext>)Conversions.doWrapArray(contexts)), contenAssist);
    this.hasSyntaxError();
  }
  
  public void loadResource(final String text) {
    try {
      final XtextResourceSet rs = this.rsp.get();
      String _primaryFileExtension = this._fileExtensionProvider.getPrimaryFileExtension();
      String _plus = ("parser." + _primaryFileExtension);
      URI _createURI = URI.createURI(_plus);
      Resource _createResource = rs.createResource(_createURI);
      this.resource = ((XtextResource) _createResource);
      StringInputStream _stringInputStream = new StringInputStream(text);
      this.resource.load(_stringInputStream, null);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public boolean hasSyntaxError() {
    final IParseResult result = this.resource.getParseResult();
    Iterable<INode> _syntaxErrors = result.getSyntaxErrors();
    int _size = IterableExtensions.size(_syntaxErrors);
    InputOutput.<Integer>println(Integer.valueOf(_size));
    Iterable<INode> _syntaxErrors_1 = result.getSyntaxErrors();
    for (final INode node : _syntaxErrors_1) {
      {
        int _startLine = node.getStartLine();
        String _plus = ("startline" + Integer.valueOf(_startLine));
        InputOutput.<String>println(_plus);
        int _endLine = node.getEndLine();
        String _plus_1 = ("startline" + Integer.valueOf(_endLine));
        InputOutput.<String>println(_plus_1);
        SyntaxErrorMessage _syntaxErrorMessage = node.getSyntaxErrorMessage();
        InputOutput.<SyntaxErrorMessage>print(_syntaxErrorMessage);
      }
    }
    return result.hasSyntaxErrors();
  }
  
  public ContentAssistContext[] getContexts(final XtextResource resource, final ITextRegion selection, final int caretOffset) {
    ContentAssistContext[] _xblockexpression = null;
    {
      IParseResult _parseResult = resource.getParseResult();
      ICompositeNode _rootNode = _parseResult.getRootNode();
      final String text = _rootNode.getText();
      int _length = text.length();
      boolean _greaterThan = (caretOffset > _length);
      if (_greaterThan) {
        return new ContentAssistContext[] {};
      }
      ContentAssistContextFactory _get = this.contextFactoryProvider.get();
      final Procedure1<ContentAssistContextFactory> _function = (ContentAssistContextFactory it) -> {
        it.setPool(this.executorService);
      };
      final ContentAssistContextFactory contextFactory = ObjectExtensions.<ContentAssistContextFactory>operator_doubleArrow(_get, _function);
      _xblockexpression = contextFactory.create(text, selection, caretOffset, resource);
    }
    return _xblockexpression;
  }
}
