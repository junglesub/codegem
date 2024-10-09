import { atom } from "recoil";

export const feedCountAtom = atom({
  key: "feedCount",
  default: -1, // Set default to 0
});
