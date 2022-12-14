import axios from 'axios'
import { API_URL } from '../.env'
import { ResponseWrapper, ErrorWrapper } from './util'

export class CoinCalculatorService {
    static async calculate ( calculateObject , snackbar , clearErrorMap) {
        try {
            const response = await axios.post(`${API_URL}/orders/prices`, calculateObject);
            return new ResponseWrapper(response, response.data)
        } catch (error) {
            throw new ErrorWrapper(error,snackbar,clearErrorMap)
        }
    }

    static async getCurrencyList ( snackbar,clearErrorMap ) {
        try {
            const response = await axios.get(`${API_URL}/currency/getCurrencyList`);
            return new ResponseWrapper(response, response.data)
        } catch (error) {
            throw new ErrorWrapper(error,snackbar,clearErrorMap)
        }
    }



}
