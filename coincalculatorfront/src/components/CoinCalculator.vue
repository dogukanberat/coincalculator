<template>
  <div class="container d-flex align-items-center justify-content-center h-100 box">
    <div class="row">
      <div class="col-12">
        <h2> {{ $t('coin_calculator.haed_text') }} </h2>
      </div>
    </div>
    <br/>
    <div class="row">
      <div class="col-12 col-md-6">
        <label class="form-label">{{ $t('currency.fiat.head') }}</label>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <input
                v-model="coin.fiatCurrency.amount"
                @keyup="cryptoCalculator('FIAT')"
                type="number"
                class="form-control"
                placeholder="0,00"
            />
          </div>
          <select
              class="form-select"
              name="currency"
              v-model="currency"
              @change="cryptoCalculator('FIAT')"
          >
            <option
                v-for="item in fiatCurrencies"
                :value="item.currency"
                :key="item.id"
            >
              {{ item.currency }}
            </option>
          </select>
        </div>
      </div>
      <div class="col-12 col-md-6">
        <label class="form-label">{{ $t('currency.coin.head') }}</label>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <input
                v-model="coin.crypto.amount"
                @keyup="cryptoCalculator('CRYPTO')"
                type="number"
                class="form-control"
                placeholder="0,00"
            />
          </div>
          <select
              class="form-select"
              name="currency"
              v-model="cryptoCurrency"
              @change="cryptoCalculator('CRYPTO')"
          >
            <option
                v-for="item in cryptoCurrencies"
                :value="item.currency"
                :key="item.id"
            >
              {{ item.currency }}
            </option>
          </select>
        </div>
      </div>
    </div>
    <div class="row">
      <div v-for="error in errors" :key="error.id"
           class="alert alert-secondary bg-danger text-white"
           role="alert"
           :class="showalert ? 'd-block' : 'd-none'"
      >
        {{ error }}
      </div>
    </div>
  </div>
</template>

<script>
import {CoinCalculatorService} from '@/services/CoinCalculatorService'

export default {
  name: "CoinCalculator",
  metaInfo: {
    title: 'COIN CALCULATOR',
    titleTemplate: '%s | SG VETERIS'
  },
  data() {
    var dataObject = {
      currency: null,
      cryptoCurrency: null,
      fiatCurrencies: [],
      cryptoCurrencies: [],
      orderType: "CRYPTO",
      coin: {
        crypto: {
          amount: 1,
          currency: "BTC"
        },
        fiatCurrency: {
          amount: 0,
          currency: "USD"
        },
        orderType: "CRYPTO"
      },
      clearTimer: null,
      timerseek: false,
      msg: "",
      errors: [],
      showalert: false,
      typingTimer: null,
      cryptoInterval: null,
    };
    return dataObject;
  },
  methods: {
    getCurrencies() {
      let self = this;
      CoinCalculatorService.getCurrencyList(this.snackbar, this.clearErrorMap).then(function (response) {
        self.fiatCurrencies = response.data.filter(currencyObject => currencyObject.isFiatCurrency)
        self.cryptoCurrencies = response.data.filter(currencyObject => !currencyObject.isFiatCurrency)
        self.cryptoCurrency = response.data.filter(currencyObject => currencyObject.currency === "BTC")[0].currency;
        self.currency = response.data.filter(currencyObject => currencyObject.currency === "USD")[0].currency;
      }).catch(function (error) {
        console.log(error);

      });
    },

    cryptoCalculator(orderType, initData) {
      let self = this;
      clearTimeout(this.typingTimer);
      this.typingTimer = setTimeout(function () {
        self.orderType = orderType;
        self.clearErrorMap();
        self.coin.fiatCurrency.amount = self.coin.fiatCurrency.amount !== "" ? self.coin.fiatCurrency.amount : 0;
        self.coin.crypto.amount = self.coin.crypto.amount !== "" ? self.coin.crypto.amount : 0;
        var data = initData != null ? initData : {
          "crypto": {
            "amount": self.orderType === "CRYPTO" ? self.coin.crypto.amount : 0,
            "currency": self.cryptoCurrency
          },
          "fiatCurrency": {
            "amount": self.orderType === "FIAT" ? self.coin.fiatCurrency.amount : 0,
            "currency": self.currency
          },
          "orderType": self.orderType
        };
        self.callCalculateServiceAndSetResponse(data);
      }, 500);

    },
    createOrStartInterval(data){
      let self =this;
      if(this.cryptoInterval){
        clearInterval(this.cryptoInterval) //Clear previous setInterval if exists
      }
      //start setInterval and store the value
      this.cryptoInterval = setInterval(function(){
        self.callCalculateServiceAndSetResponse(data);
        // codes
      }, 10*1000);
    },
    callCalculateServiceAndSetResponse(data){
      let self = this;
      CoinCalculatorService.calculate(data, self.snackbar, self.clearErrorMap).then(function (response) {
        self.coin = response.data;
        self.createOrStartInterval(data);
      }).catch(function (error) {
        console.log(error);
      });
    },
    snackbar(msg) {
      this.errors.push(msg);
      this.showalert = true;
    },
    clearErrorMap() {
      this.errors = []
      this.showalert = false;
    },
  },
  created() {
    document.title = "SG VETERIS";
    this.getCurrencies();
    this.cryptoCalculator(this.orderType, this.coin);

  },
};
</script>
<style lang="css" scoped>
.box {
  border: 1px solid black;
  border-radius: 5px;
  width: 40%;
  background: white;
  padding: 50px 20px;
  margin-top: 20px;
}

@media only screen and (max-width: 600px) {
  .box {
    width: 90%;
  }
}

@media only screen and (max-width: 1515px) {
  .box {
    width: 90%;
  }
}

.form-control {
  border-radius: 5px 0px 0px 5px;
}

.form-control:focus {
  outline-style: none;
  box-shadow: none;
  border: 1px solid #ced4da;
}

.form-select:focus {
  outline-style: none;
  box-shadow: none;
  border: 1px solid #ced4da;
}

.container {
  flex-direction: column;

}

/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
</style>

