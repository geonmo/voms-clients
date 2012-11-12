package org.italiangrid.voms.clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.italiangrid.voms.clients.impl.DefaultProxyDestroyBehaviour;
import org.italiangrid.voms.clients.impl.ProxyDestroyListenerHelper;
import org.italiangrid.voms.clients.options.v2.CLIOption;
import org.italiangrid.voms.clients.options.v2.ProxyDestroyOptions;

/**
 * This class implements the voms-proxy-destroy command-line client.
 * 
 * @author valerioventuri
 * 
 */
public class VomsProxyDestroy extends AbstractCLI {

  /**
   * The command name.
   */
  private static final String COMMAND_NAME = "voms-proxy-destroy";

  /**
   * 
   */
  private ProxyDestroyListenerHelper listenerHelper;
  
	/**
	 * The main.
	 * 
	 * @param args an array of {@link String} containing commman line options.
	 */
	public static void main(String[] args) {

		new VomsProxyDestroy(args);
	}

	/**
	 * 
	 * Constructor.
	 * 
	 * @param args an array of {@link String} containing command line options.
	 */
	public VomsProxyDestroy(String[] args) {
	  super(COMMAND_NAME);
	  
		initOptions();
		parseOptionsFromCommandLine(args);
	  listenerHelper = new ProxyDestroyListenerHelper(logger);
		execute();
	}

	/**
	 * Initialize options.
	 * 
	 */
	private void initOptions() {

	  List<CLIOption> options = new ArrayList<CLIOption>();
    
    options.addAll(Arrays.asList(ProxyDestroyOptions.values()));
    
    initOptions(options);
	}

	@Override
	protected void execute() {
	  
	  ProxyDestroyParams params = getProxyDestroyParamsFromCommandLine(commandLine);

	  new DefaultProxyDestroyBehaviour(listenerHelper).destroyProxy(params);
	}

	private ProxyDestroyParams getProxyDestroyParamsFromCommandLine(CommandLine commandLine) {
	  
	  ProxyDestroyParams params = new ProxyDestroyParams();
    
    if (commandLineHasOption(ProxyDestroyOptions.DRY)) {
      params.setDryRun(true);
    }

    if (commandLineHasOption(ProxyDestroyOptions.FILE)) {
      params.setProxyFile(getOptionValue(ProxyDestroyOptions.FILE));
    }
    
    return params;
	}
	
}
