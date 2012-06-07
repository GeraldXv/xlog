package hk.hku.cs.xlog.connect;


import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * Listens for service provider connection events.
 * Allows for custom logic to be executed before and after connections are established with a specific service provider.
 * @author Keith Donald
 * @author Craig Walls
 * @param <S> The service API hosted by the intercepted service provider.
 */
public interface ConnectInterceptor<S> {
	
	/**
	 * Called during connection initiation, immediately before user authorization.
	 * May be used to store custom connection attributes in the session before redirecting the user to the provider's site or to contribute parameters to the authorization URL.
	 */
	void preConnect(ConnectionFactory<S> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request);

	/**
	 * Called immediately after the connection is established.
	 * Used to invoke the service API on behalf of the user upon connecting.
	 */
	void postConnect(Connection<S> connection, WebRequest request);
	
}
