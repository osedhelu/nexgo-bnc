package com.disglobal.bnc.DigipayApi.domain.entities


/**
 * Respuesta de validación de terminal
 */
data class TerminalValidationResponse(
    val id: Int,                  // DigipayID
    val merchant: String,         // El nombre del comercio
    val terminal: String,         // Información del terminal
    val lotNumber: String,        // Número de lotes
    val stan: String,             // System Trace Audit Number
    val statusId: Int,            // ID Status
    val description: String,      // Descripción Status
    val commerceId: Int,          // ID del comercio
    val taxId: String,            // RIF del comercio
    val name: String,             // Nombre comercial
    val address: String,          // Dirección comercial
    val commerceStatusId: Int,    // Status del comercio
    val deviceId: Int,            // ID del dispositivo
    val serial: String,           // Serial del terminal
    val deviceStatusId: Int,      // Status del dispositivo
    val urlBase: String,          // Url Base para las API
    val key001: String,           // Key 001 DUKPT
    val key002: String,           // Key 002 DUKPT
    val ksn: String,              // Key Serial Number
    val processorName: String,    // Nombre del procesador
    val ipAddress: String,        // El IP del procesador
    val ipPort: String,           // El puerto del procesador
    val acquirerName: String,     // Nombre del banco responsable
    val acquirerValue: String,    // ID del banco responsable
    val acquirerTaxId: String,    // RIF del banco responsable
    val header: String            // Información cabecera
)

/**
 * Respuesta de transacción
 */
data class TransactionResponse(
    val id: Int,
    val tranDate: String,                 // Fecha de la transacción
    val serial: String,                   // Serial del merchant
    val taxId: String,                    // Rif del merchant
    val mit: String,
    val lotNumber: String,                // Número de lote
    val affiliationId: Int,               // ID
    val applicationIdentifier: String,    // Identificación Aplicación
    val applicationLabel: String,         // Etiqueta de Aplicación
    val upn: String,                      // Unpredictable number
    val d002: String,                     // PAN (Primary account number)
    val d003: String,                     // Processing code
    val d004: String,                     // Transaction amount
    val d007: String,                     // Transmission datetime (MMddhhmmss)
    val d011: String,                     // STAN (Systems trace audit number)
    val d012: String,                     // Time transaction (hhmmss)
    val d022: String,                     // PIN/PAN entry mode
    val d024: String,                     // Network International identifier
    val d037: String,                     // Retrieval reference number
    val d038: String,                     // Authorisation identification response
    val d039: String,                     // Response code
    val d041: String,                     // Card acceptor terminal identification
    val d042: String,                     // Card acceptor identification code
    val tranDateResp: String,             // Marca de tiempo de respuesta
    val receipt: String,                  // Recibo
    val signature: String                 // Firma del cliente
)

/**
 * Respuesta de resumen de lote
 */
data class BatchSummaryResponse(
    val lotNumber: Int,                   // Número del Lote
    val quantityRefund: Int,              // Cantidad de anulaciones
    val amountRefund: String,             // Monto de anulaciones
    val quantitySales: Int,               // Cantidad de ventas
    val amountSales: String,              // Monto total de ventas
    val quantitySalesDebitApproved: Int,  // Cantidad de ventas por débito
    val amountSalesDebitApproved: String, // Monto de ventas por débito
    val quantitySalesCreditApproved: Int, // Cantidad de ventas por crédito
    val amountSalesCreditApproved: String // Monto de ventas por crédito
)

/**
 * Respuesta de procesamiento de transacción
 */
data class TransactionProcessResponse(
    val status: Int,     // Tag 8A (Authentication Response Code)
    val recibo: String?  // Tag DF832A (Receipt) - Solo Venta y Anulación
)