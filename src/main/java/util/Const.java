package util;

public class Const {
	public static final String CERT_TEXT = "MIIO0AYJKoZIhvcNAQcCoIIOwTCCDr0CAQExDjAMBgoqhiQCAQEBAQIBMAsGCSqGSIb3DQEHAaCCB0Mwggc/MIIG56ADAgECAhQiM1zMbyGb0gQAAAD/JwAARQIBADANBgsqhiQCAQEBAQMBATCB8jEkMCIGA1UECgwb0JDQoiAi0K<br>\n"
			+ "PQutGA0KHQuNCx0LHQsNC90LoiMSMwIQYDVQQLDBrQk9GA0YPQv9CwINCj0KbQodCaINCh0IbQkTFrMGkGA1UEAwxi0JDQptCh0Jog0J/Rg9Cx0LvRltGH0L3QvtCz0L4g0LDQutGG0ZbQvtC90LXRgNC90L7Qs9C+INGC0L7QstCw0YDQuNGB0YLQstCw<br>\n"
			+ "ICLQo9C60YDQodC40LHQsdCw0L3QuiIxGDAWBgNVBAUMD1VBLTA5ODA3NzUwLTAwMjELMAkGA1UEBhMCVUExETAPBgNVBAcMCNCa0LjRl9CyMB4XDTE3MTEyMDIyMDAwMFoXDTE5MTEyMDIyMDAwMFowggFgMSQwIgYDVQQKDBvQkNCiICLQo9C60YDQod<br>\n"
			+ "C40LHQsdCw0L3QuiIxHDAaBgNVBAsME9Cm0KDQhtChINCT0KLQoNCG0KAxPzA9BgNVBAwMNtC/0YDQvtCy0ZbQtNC90LjQuSDRltC90LbQtdC90LXRgC3Qv9GA0L7Qs9GA0LDQvNGW0YHRgjE9MDsGA1UEAww00J/QuNGB0LDRgNC10L3QutC+INCQ0L3Q<br>\n"
			+ "tNGA0ZbQuSDQk9GA0LjQs9C+0YDQvtCy0LjRhzEbMBkGA1UEBAwS0J/QuNGB0LDRgNC10L3QutC+MSowKAYDVQQqDCHQkNC90LTRgNGW0Lkg0JPRgNC40LPQvtGA0L7QstC40YcxDjAMBgNVBAUMBTEwMjM5MQswCQYDVQQGEwJVQTEVMBMGA1UEBwwM0K<br>\n"
			+ "XQsNGA0LrRltCyMR0wGwYDVQQIDBTQpdCw0YDQutGW0LLRgdGM0LrQsDCB8jCByQYLKoYkAgEBAQEDAQEwgbkwdTAHAgIBAQIBDAIBAAQhEL7j22rqnh+GV4xFwSWU/5QjlKfXOPkYfmUVAXKU9M4BAiEAgAAAAAAAAAAAAAAAAAAAAGdZITrxgumH0+F3<br>\n"
			+ "FJB9Rw0EIbYP0tjc6Kk0I8YQG8qRxHoAfmwwCybNVWybDn0g7ykqAARAqdbrRfE8cIKAxJZ7Ix9erfZY66TANykdONlr8CXKThf46XINxhW0OiiXXwvB3qNkOLVk6iwXn9ASPm24+sV5BAMkAAQh12rleOEM8nQ5nqJsiRmy8Ecy3PkYkECn67hcHgZ9dX<br>\n"
			+ "0Bo4IDSzCCA0cwKQYDVR0OBCIEIGI1zqIwiWWzpL/vuP/dJwCUBWoAPiNeCZQIcwnyHrT7MCsGA1UdIwQkMCKAICIzXMxvIZvSXi3VnKUHve5FCJGNMpHPQRXf+juOE4jLMC8GA1UdEAQoMCagERgPMjAxNzExMjAyMjAwMDBaoREYDzIwMTkxMTIwMjIw<br>\n"
			+ "MDAwWjAOBgNVHQ8BAf8EBAMCBsAwGgYDVR0lAQH/BBAwDgYMKwYBBAGBl0YBAQgVMBkGA1UdIAEB/wQPMA0wCwYJKoYkAgEBAQICMAwGA1UdEwEB/wQCMAAwHgYIKwYBBQUHAQMBAf8EDzANMAsGCSqGJAIBAQECATCBnQYDVR0RBIGVMIGSoFEGDCsGAQ<br>\n"
			+ "QBgZdGAQEEAqBBDD82MTAwMSwg0LwuINCl0LDRgNC60ZbQsiwg0L/RgNC+0YHQvy4g0JzQvtGB0LrQvtCy0YHRjNC60LjQuSwgNjCgHAYMKwYBBAGBl0YBAQQBoAwMCjA2NzUzMDUxNTCBH2FuZHJpaS5weXNhcmVua29AdWtyc2liYmFuay5jb20wTQYD<br>\n"
			+ "VR0fBEYwRDBCoECgPoY8aHR0cDovL2Nzay51a3JzaWJiYW5rLmNvbS9kb3dubG9hZC9jcmxzL0NBLTIyMzM1Q0NDLUZ1bGwuY3JsME4GA1UdLgRHMEUwQ6BBoD+GPWh0dHA6Ly9jc2sudWtyc2liYmFuay5jb20vZG93bmxvYWQvY3Jscy9DQS0yMjMzNU<br>\n"
			+ "NDQy1EZWx0YS5jcmwwgYAGCCsGAQUFBwEBBHQwcjA0BggrBgEFBQcwAYYoaHR0cDovL2Nzay51a3JzaWJiYW5rLmNvbS9zZXJ2aWNlcy9vY3NwLzA6BggrBgEFBQcwAoYuaHR0cDovL2Nzay51a3JzaWJiYW5rLmNvbS9jYS1jZXJ0aWZpY2F0ZXMvLnA3<br>\n"
			+ "YjBDBggrBgEFBQcBCwQ3MDUwMwYIKwYBBQUHMAOGJ2h0dHA6Ly9jc2sudWtyc2liYmFuay5jb20vc2VydmljZXMvdHNwLzBABgNVHQkEOTA3MBwGDCqGJAIBAQELAQQBATEMEwoyODQ2NDA5ODMxMBcGDCqGJAIBAQELAQQHATEHEwU1NDY3ODANBgsqhi<br>\n"
			+ "QCAQEBAQMBAQNDAARA0jIquSb0Ms14iUYagROJMPEmqA1ERjush+Su3JSXbG365duL55XeVZBiv0ZnEK2Zt3viXrVpfkNFxOar7IuKSDGCB1IwggdOAgEBMIIBCzCB8jEkMCIGA1UECgwb0JDQoiAi0KPQutGA0KHQuNCx0LHQsNC90LoiMSMwIQYDVQQL<br>\n"
			+ "DBrQk9GA0YPQv9CwINCj0KbQodCaINCh0IbQkTFrMGkGA1UEAwxi0JDQptCh0Jog0J/Rg9Cx0LvRltGH0L3QvtCz0L4g0LDQutGG0ZbQvtC90LXRgNC90L7Qs9C+INGC0L7QstCw0YDQuNGB0YLQstCwICLQo9C60YDQodC40LHQsdCw0L3QuiIxGDAWBg<br>\n"
			+ "NVBAUMD1VBLTA5ODA3NzUwLTAwMjELMAkGA1UEBhMCVUExETAPBgNVBAcMCNCa0LjRl9CyAhQiM1zMbyGb0gQAAAD/JwAARQIBADAMBgoqhiQCAQEBAQIBoIIF2TAYBgkqhkiG9w0BCQMxCwYJKoZIhvcNAQcBMBwGCSqGSIb3DQEJBTEPFw0xODEwMDkx<br>\n"
			+ "NTM1MDFaMC8GCSqGSIb3DQEJBDEiBCAFhi1xqbuwC3xUNtdtkSwBSmSv1MlC+IokkEfI3qs5OzCCAWIGCyqGSIb3DQEJEAIvMYIBUTCCAU0wggFJMIIBRTAMBgoqhiQCAQEBAQIBBCDcGZqvml5MKv6UM3Wq9u50HIik3ZyTjf6uP3kRStdhNDCCAREwgf<br>\n"
			+ "ikgfUwgfIxJDAiBgNVBAoMG9CQ0KIgItCj0LrRgNCh0LjQsdCx0LDQvdC6IjEjMCEGA1UECwwa0JPRgNGD0L/QsCDQo9Cm0KHQmiDQodCG0JExazBpBgNVBAMMYtCQ0KbQodCaINCf0YPQsdC70ZbRh9C90L7Qs9C+INCw0LrRhtGW0L7QvdC10YDQvdC+<br>\n"
			+ "0LPQviDRgtC+0LLQsNGA0LjRgdGC0LLQsCAi0KPQutGA0KHQuNCx0LHQsNC90LoiMRgwFgYDVQQFDA9VQS0wOTgwNzc1MC0wMDIxCzAJBgNVBAYTAlVBMREwDwYDVQQHDAjQmtC40ZfQsgIUIjNczG8hm9IEAAAA/ycAAEUCAQAwggQGBgsqhkiG9w0BCR<br>\n"
			+ "ACFDGCA/UwggPxBgkqhkiG9w0BBwKgggPiMIID3gIBAzEOMAwGCiqGJAIBAQEBAgEwagYLKoZIhvcNAQkQAQSgWwRZMFcCAQEGCiqGJAIBAQECAwEwMDAMBgoqhiQCAQEBAQIBBCAFhi1xqbuwC3xUNtdtkSwBSmSv1MlC+IokkEfI3qs5OwIDFWo9GA8y<br>\n"
			+ "MDE4MTAwOTE1MzI0NloxggNbMIIDVwIBATCCARMwgfoxPzA9BgNVBAoMNtCc0ZbQvdGW0YHRgtC10YDRgdGC0LLQviDRjtGB0YLQuNGG0ZbRlyDQo9C60YDQsNGX0L3QuDExMC8GA1UECwwo0JDQtNC80ZbQvdGW0YHRgtGA0LDRgtC+0YAg0IbQotChIN<br>\n"
			+ "Cm0JfQnjFJMEcGA1UEAwxA0KbQtdC90YLRgNCw0LvRjNC90LjQuSDQt9Cw0YHQstGW0LTRh9GD0LLQsNC70YzQvdC40Lkg0L7RgNCz0LDQvTEZMBcGA1UEBQwQVUEtMDAwMTU2MjItMjAxMjELMAkGA1UEBhMCVUExETAPBgNVBAcMCNCa0LjRl9CyAhQw<br>\n"
			+ "BHUd7yx4rgIAAAABAAAAewAAADAMBgoqhiQCAQEBAQIBoIIB2jAaBgkqhkiG9w0BCQMxDQYLKoZIhvcNAQkQAQQwHAYJKoZIhvcNAQkFMQ8XDTE4MTAwOTE1MzI0NlowLwYJKoZIhvcNAQkEMSIEIEw0YsWc5ABlMqEyHd7uo9VhOdYyW4Je3zLKz30z25<br>\n"
			+ "GiMIIBawYLKoZIhvcNAQkQAi8xggFaMIIBVjCCAVIwggFOMAwGCiqGJAIBAQEBAgEEIOOT893WzluNhPJIRUfsJq2lruDHcVFe7SravBjHWW44MIIBGjCCAQCkgf0wgfoxPzA9BgNVBAoMNtCc0ZbQvdGW0YHRgtC10YDRgdGC0LLQviDRjtGB0YLQuNGG<br>\n"
			+ "0ZbRlyDQo9C60YDQsNGX0L3QuDExMC8GA1UECwwo0JDQtNC80ZbQvdGW0YHRgtGA0LDRgtC+0YAg0IbQotChINCm0JfQnjFJMEcGA1UEAwxA0KbQtdC90YLRgNCw0LvRjNC90LjQuSDQt9Cw0YHQstGW0LTRh9GD0LLQsNC70YzQvdC40Lkg0L7RgNCz0L<br>\n"
			+ "DQvTEZMBcGA1UEBQwQVUEtMDAwMTU2MjItMjAxMjELMAkGA1UEBhMCVUExETAPBgNVBAcMCNCa0LjRl9CyAhQwBHUd7yx4rgIAAAABAAAAewAAADANBgsqhiQCAQEBAQMBAQRA6BUK4rX17/SxP/i2EIprTRTjy1YmP1KmXJNwkgid2B9ZTJAigwjBqngU<br>\n"
			+ "v1+NwvIK0N5oN/5KXjrCCCPuUn94BDANBgsqhiQCAQEBAQMBAQRAHcf9rdee6kmSIvkQAFjNJyleVhQNgThRZI+YLVW94DqyWCadMj9ksvpavrnvg/iUyLEs9mMsOK6AXDQ3RDt/TQ==";

}
