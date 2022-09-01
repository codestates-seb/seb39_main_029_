import { atom } from "recoil";
// import { recoilPersist } from "recoil-persist";

// const { persistAtom } = recoilPersist();

export const TokenState = atom({
  key: "TokenState",
  default: "",
  // effects_UNSTABLE: [persistAtom],
});
