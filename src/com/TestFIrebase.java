package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class TestFIrebase {
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf_FIREBASE = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	private static DatabaseReference currentRef;
	private static FirebaseDatabase ref;
	private static String hh = "19  ,20  ,21  ,23  ,24  ,25  ,26  ,27  ,28  ,29  ,30  ,147  ,402  ,428  ,429  ,430  ,432  ,433  ,434  ,436  ,441  ,442  ,443  ,444  ,445  ,446  ,447  ,448  ,450  ,451  ,452  ,453  ,454  ,455  ,457  ,459  ,460  ,461  ,592  ,2036  ,2041  ,2042  ,2044  ,2045  ,2047  ,2048  ,2049  ,2050  ,2051  ,2052  ,2053  ,2055  ,2056  ,2057  ,2058  ,2061  ,2062  ,2063  ,2064  ,2066  ,2067  ,2068  ,2070  ,2071  ,2072  ,2073  ,2104  ,2105  ,2107  ,2108  ,2110  ,2111  ,2112  ,2113  ,2114  ,2115  ,2116  ,2117  ,2118  ,2119  ,2120  ,2121  ,2122  ,2123  ,2124  ,2125  ,2126  ,2127  ,2128  ,2129  ,2130  ,2131  ,2132  ,2133  ,2134  ,2135  ,2136  ,2137  ,2138  ,2139  ,2140  ,2141  ,2142  ,2143  ,2144  ,2145  ,2146  ,2147  ,2148  ,2149  ,2150  ,2151  ,2152  ,2153  ,2154  ,2155  ,2156  ,2157  ,2158  ,2159  ,2160  ,2161  ,2162  ,2163  ,2164  ,2165  ,2166  ,2167  ,2168  ,2169  ,2170  ,2171  ,2172  ,2173  ,2174  ,2175  ,2176  ,2177  ,2178  ,2179  ,2180  ,2181  ,2182  ,2183  ,2184  ,2185  ,2186  ,2187  ,2188  ,2189  ,2190  ,2191  ,2192  ,2193  ,2194  ,2195  ,2196  ,2197  ,2198  ,2199  ,2200  ,2201  ,2202  ,2203  ,2204  ,2205  ,2206  ,2207  ,2208  ,2209  ,2210  ,2211  ,2212  ,2213  ,2214  ,2215  ,2216  ,2217  ,2218  ,2219  ,2220  ,2221  ,2222  ,2223  ,2224  ,2225  ,2226  ,2227  ,2228  ,2229  ,2230  ,2231  ,2232  ,2233  ,2234  ,2235  ,2236  ,2237  ,2238  ,2239  ,2240  ,2241  ,2242  ,2243  ,2244  ,2317  ,2318  ,2319  ,2320  ,2321  ,2322  ,2323  ,2324  ,2325  ,2326  ,2327  ,2328  ,2329  ,2330  ,2331  ,2332  ,2333  ,2334  ,2335  ,2337  ,2338  ,2339  ,2340  ,2341  ,2342  ,2343  ,2344  ,2345  ,2346  ,2347  ,2348  ,2349  ,2350  ,2351  ,2352  ,2353  ,2354  ,2355  ,2356  ,2357  ,2358  ,2359  ,2360  ,2361  ,2362  ,2363  ,2364  ,2365  ,2366  ,2367  ,2368  ,2369  ,2370  ,2371  ,2372  ,2373  ,2374  ,2387  ,2389  ,2391  ,2396  ,2403  ,2404  ,2413  ,2417  ,2458  ,2461  ,2481  ,2673  ,2674  ,2831  ,3305  ,3306  ,3307  ,3331  ,3391  ,4527  ,4529  ,4531  ,4532  ,4536  ,4537  ,4538  ,4539  ,4543  ,4544  ,4547  ,4548  ,4549  ,4552  ,4553  ,4595  ,4620  ,4652  ,4653  ,4654  ,4655  ,4656  ,4659  ,4691  ,4698  ,4699  ,4708  ,4711  ,4712  ,4713  ,4722  ,4724  ,4726  ,4728  ,4735  ,4745  ,4751  ,4785  ,4788  ,4989  ,4990  ,4991  ,4992  ,4993  ,4994  ,4995  ,4996  ,4997  ,4998  ,4999  ,5000  ,5002  ,5003  ,5004  ,5006  ,5008  ,5009  ,5011  ,5012  ,5017  ,5098  ,5195  ,5359  ,5365  ,5386  ,5387  ,5388  ,5389  ,5390  ,5391  ,5392  ,5393  ,5395  ,5458  ,5459  ,5461  ,5976  ,6035  ,6055  ,6056  ,6057  ,6059  ,6060  ,6063  ,6064  ,6066  ,6067  ,6069  ,6070  ,6071  ,6072  ,6073  ,6074  ,6075  ,6080  ,6081  ,6082  ,6083  ,6087  ,6088  ,6164  ,6168  ,6170  ,6171  ,6172  ,6340  ,6354  ,6355  ,6356  ,6357  ,6358  ,6359  ,6360  ,6361  ,6362  ,6363  ,6364  ,6365  ,6366  ,6367  ,6368  ,6369  ,6370  ,6371  ,6372  ,6373  ,6374  ,6375  ,6376  ,6377  ,6378  ,6379  ,6380  ,6381  ,6382  ,6383  ,6384  ,6385  ,6386  ,6387  ,6388  ,6389  ,6390  ,6391  ,6392  ,6393  ,6394  ,6395  ,6396  ,6397  ,6398  ,6399  ,6400  ,6401  ,6402  ,6403  ,6404  ,6405  ,6406  ,6407  ,6408  ,6409  ,6410  ,6411  ,6412  ,6556  ,6560  ,6747  ,6749  ,6750  ,6751  ,6752  ,6753  ,6754  ,6760  ,6762  ,6764  ,6766  ,6774  ,6775  ,6776  ,6777  ,6778  ,6779  ,6780  ,6781  ,6782  ,6783  ,6784  ,6786  ,6787  ,6788  ,6790  ,6793  ,6795  ,6805  ,6806  ,6818  ,6819  ,6820  ,6821  ,6822  ,6823  ,6824  ,6825  ,6827  ,6828  ,6831  ,6832  ,6834  ,6835  ,6836  ,6839  ,6843  ,6844";

	public static void main(String args[]) {
		try {
			InputStream targetStream = new TestFIrebase().getClass().getClassLoader()
					.getResourceAsStream("Viksit-ac716147c574.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setDatabaseUrl("https://fir-viksit.firebaseio.com/").setServiceAccount(targetStream)
					.build();
			FirebaseApp.initializeApp(options);
			System.out.println("ServletContextListener started");
			ref = FirebaseDatabase.getInstance();
			DatabaseReference jj = ref.getReference("istar-notification").child("999999");
			HashMap<String,Object > kk = new HashMap<>();
			kk.put("message", "How r ");
			kk.put("time",sdf.format(new Date()));
			kk.put("type","MESSAGE");
			kk.put("id", 25);
			HashMap<String,Object > item = new HashMap<>();
			item.put("lessonId","6");
			item.put("taskId","1");
			item.put("courseId","5");
			kk.put("item",item);
			
			jj.push().setValue(kk);

			

			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}