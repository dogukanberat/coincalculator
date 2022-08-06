<template>
  <div class="container d-flex align-items-center justify-content-center h-100">
    <div class="row">
      <div class="col-12">
        <h2>Currency to Coin Calculator</h2>
      </div>
    </div>
    <br />
    <div class="row">
      <div class="col-12 col-md-6">
        <label class="form-label">Amount the Spend</label>
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
      </div>      <div class="col-12 col-md-6">
      <label class="form-label">Coins to Receive</label>
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
      <div id="countdown" :class="timerseek ? 'd-block' : 'd-none'"></div>
    </div>
    <div class="row">
      <div   v-for="error in errors" :key="error.id"
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
import { CoinCalculatorService } from '@/services/CoinCalculatorService'
import { mapActions } from "vuex";
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
      orderType : "CRYPTO",
      coin: {
        crypto: {
          amount: 1,
          currency: "BTC"
        },
        fiatCurrency: {
          amount: 0,
          currency: "USD"
        },
        orderType : "CRYPTO"
      },
      clearTimer: null,
      timerseek: false,
      msg: "",
      errors: [],
      showalert: false,
    };
    return dataObject;
  },
  methods: {
    ...mapActions(["offerSetter"]),
    getCurrencies() {
  let self = this;
      CoinCalculatorService.getCurrencyList(this.snackbar).then(function (response) {
        self.fiatCurrencies = response.data.filter(currencyObject => currencyObject.isFiatCurrency)
        self.cryptoCurrencies = response.data.filter(currencyObject => !currencyObject.isFiatCurrency)
        self.cryptoCurrency = response.data.filter(currencyObject => currencyObject.currency === "BTC")[0].currency;
        self.currency = response.data.filter(currencyObject => currencyObject.currency === "USD")[0].currency;
      }).catch(function (error) {
        console.log(error);

      });
      // axios({
      //   method: "get",
      //   url: `https://blockchain.info/ticker`,
      // })
      //     .then((response) => {
      //       console.lolg
      //       this.fiatCurrencies = response.data.filter(currencyObject => currencyObject.isFiatCurrency)
      //       this.currency = response.data.filter(currencyObject => currencyObject.currency === "USD").currency;
      //       if (document.cookie.includes("currency")) {
      //         const cookiecurrency = document.cookie.split("currency=")[1];
      //         this.currency = cookiecurrency;
      //       } else {
      //         document.cookie = `currency=${this.currency}; expires=Fri, 31 Dec 9999 23:59:59 GMT; SameSite=None; Secure`;
      //       }
      //     })
    },

    cryptoCalculator(orderType,initData) {
      let self = this;
      this.orderType = orderType;
      this.showalert = false;
      this.errors = [];
      var data = initData != null ? initData : {
        "crypto": {
          "amount": this.orderType === "CRYPTO" ? this.coin.crypto.amount : null,
          "currency": this.cryptoCurrency
        },
        "fiatCurrency": {
          "amount":  this.orderType === "FIAT" ? this.coin.fiatCurrency.amount : null,
          "currency": this.currency
        },
        "orderType": this.orderType
      };
      CoinCalculatorService.calculate(data,this.snackbar).then(function (response) {
        self.coin = response.data;
      }).catch(function (error) {
        console.log(error);

      });
    },
    snackbar(msg) {
      this.errors.push(msg);
      this.showalert = true;
    },
    onChange() {
      if(this.coin.fiatCurrency.amount) {
        this.calculate();
      }
    },
  },
  created() {
    document.title = "SG VETERIS";
    this.getCurrencies();
    this.cryptoCalculator(this.orderType,this.coin);
  },
};
</script>
<style lang="css" scoped>
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
</style>
